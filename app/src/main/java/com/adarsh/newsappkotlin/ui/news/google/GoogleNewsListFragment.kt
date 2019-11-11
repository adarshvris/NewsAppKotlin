package com.adarsh.newsappkotlin.ui.news.google


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.work.WorkInfo
import com.adarsh.newsappkotlin.R
import com.adarsh.newsappkotlin.api.*
import com.adarsh.newsappkotlin.constants.GOOGLE_NEWS_LIST
import com.adarsh.newsappkotlin.constants.GOOGLE_SOURCE
import com.adarsh.newsappkotlin.constants.SOURCE
import com.adarsh.newsappkotlin.databinding.FragmentGoogleNewsListBinding
import com.adarsh.newsappkotlin.di.InjectableInterface
import com.adarsh.newsappkotlin.extension.fromJson
import com.adarsh.newsappkotlin.extension.toast
import com.adarsh.newsappkotlin.extension.vmProvider
import com.adarsh.newsappkotlin.ui.adapters.GoogleNewsAdapter
import com.adarsh.newsappkotlin.ui.vm.NewsVM
import com.adarsh.newsappkotlin.util.Resource
import com.adarsh.newsappkotlin.util.isNetworkAvailable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_google_news_list.*
import kotlinx.android.synthetic.main.fragment_google_news_list.btnRetry
import kotlinx.android.synthetic.main.fragment_google_news_list.llLoading
import kotlinx.android.synthetic.main.fragment_google_news_list.srlRefresh
import kotlinx.android.synthetic.main.fragment_indian_news_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class GoogleNewsListFragment : Fragment(), InjectableInterface {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var newsVM: NewsVM

    private var isOnline: Boolean = false

    private lateinit var googleNewsListAdapter: GoogleNewsAdapter
    private var googleNewsDetailList = ArrayList<GoogleNewsDetail>()

    private lateinit var newsListFragmentBinding: FragmentGoogleNewsListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        newsListFragmentBinding = FragmentGoogleNewsListBinding.inflate(inflater, container, false)
        return newsListFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vmProvider<NewsVM>(viewModelFactory) {
            newsVM = it
        }

        googleNewsListAdapter = GoogleNewsAdapter(googleNewsDetailList) {
            view.findNavController().navigate(
                GoogleNewsListFragmentDirections.actionNewsListFragmentToNewsDetailFragment(
                    it
                )
            )
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        isOnline = isNetworkAvailable(context!!)

        newsListFragmentBinding.rvLoadNewsList.adapter = googleNewsListAdapter

        getGoogleNewsList()

        btnRetry.setOnClickListener {
            scheduleGoogleNewsListWorkManager()
        }

        srlRefresh.setOnRefreshListener {
            getGoogleNewsList()
        }

    }

    private fun getGoogleNewsList(isOnline: Boolean = true) {
        CoroutineScope(Dispatchers.Main).launch {
            newsVM.getGoogleNewsList(GOOGLE_SOURCE, isOnline)
                .observe(this@GoogleNewsListFragment, Observer {
                    srlRefresh.isRefreshing = false
                    when (it.status) {
                        Resource.Status.LOADING -> {
                            llLoading.visibility = VISIBLE
                            btnRetry.visibility = GONE
                        }

                        Resource.Status.ERROR -> {
                            llLoading.visibility = GONE
                            if (googleNewsDetailList.isEmpty()) {
                                btnRetry.visibility = VISIBLE
                                context!!.toast(getString(R.string.no_internet_connection))
                            }
                        }

                        Resource.Status.SUCCESS -> {
                            llLoading.visibility = GONE
                            btnRetry.visibility = GONE
                            Log.e("response", "Size ${it.data?.size} data ${it.data}")
                            googleNewsDetailList.clear()
                            it.data?.let { it1 -> googleNewsDetailList.addAll(it1) }
                            googleNewsListAdapter.notifyDataSetChanged()
                        }
                    }
                })
        }
    }

    private fun scheduleGoogleNewsListWorkManager() {
        val data = HashMap<String, String>()
        data[SOURCE] = GOOGLE_SOURCE

        CoroutineScope(Dispatchers.Main).launch {
            newsVM.getGoogleNewsListUsingWorkManager(
                "scheduleGoogleNewsListWorkManager", data
            ).observe(this@GoogleNewsListFragment, Observer {
                when (it.state) {
                    WorkInfo.State.RUNNING -> {
                        llLoading.visibility = VISIBLE
                        btnRetry.visibility = GONE
                    }

                    WorkInfo.State.SUCCEEDED -> {
                        getGoogleNewsList(false)
                    }

                    WorkInfo.State.FAILED -> {
                        llLoading.visibility = GONE
                        btnRetry.visibility = VISIBLE
                    }

                }

            })
        }
    }

}
