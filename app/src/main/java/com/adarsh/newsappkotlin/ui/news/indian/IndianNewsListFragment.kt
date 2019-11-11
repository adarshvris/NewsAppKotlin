package com.adarsh.newsappkotlin.ui.news.indian


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
import com.adarsh.newsappkotlin.api.IndianNewsDetail
import com.adarsh.newsappkotlin.constants.SOURCE
import com.adarsh.newsappkotlin.databinding.FragmentIndianNewsListBinding
import com.adarsh.newsappkotlin.di.InjectableInterface
import com.adarsh.newsappkotlin.extension.toast
import com.adarsh.newsappkotlin.extension.vmProvider
import com.adarsh.newsappkotlin.ui.adapters.IndianNewsAdapter
import com.adarsh.newsappkotlin.ui.vm.NewsVM
import com.adarsh.newsappkotlin.util.Resource
import com.adarsh.newsappkotlin.util.isNetworkAvailable
import kotlinx.android.synthetic.main.fragment_indian_news_list.*
import kotlinx.android.synthetic.main.fragment_indian_news_list.btnRetry
import kotlinx.android.synthetic.main.fragment_indian_news_list.llLoading
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class IndianNewsListFragment : Fragment(), InjectableInterface {

    private lateinit var fragmentIndianNewsListBinding: FragmentIndianNewsListBinding

    private var isOnline: Boolean = false

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var newsVM: NewsVM

    private lateinit var indianNewsAdapter: IndianNewsAdapter

    private var indianNewsList = ArrayList<IndianNewsDetail>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentIndianNewsListBinding =
            FragmentIndianNewsListBinding.inflate(inflater, container, false)
        return fragmentIndianNewsListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        indianNewsAdapter = IndianNewsAdapter(indianNewsList) {
            view.findNavController().navigate(
                IndianNewsListFragmentDirections.actionIndianNewsListFragmentToIndianNewsDetailFragment(
                    it
                )
            )
        }

        fragmentIndianNewsListBinding.rvIndianRecyclerView.adapter = indianNewsAdapter
        isOnline = isNetworkAvailable(context!!)

        vmProvider<NewsVM>(viewModelFactory) {
            newsVM = it
        }

        getIndianNewsDetail()

        btnRetry.setOnClickListener {
            scheduleIndianNewsListWorkManager()
        }

        srlRefresh.setOnRefreshListener {
            getIndianNewsDetail()
        }
    }

    private fun getIndianNewsDetail(isOnline: Boolean = true) {
        CoroutineScope(Dispatchers.Main).launch {
            newsVM.getIndianNewsList("in", isOnline).observe(this@IndianNewsListFragment, Observer {
                srlRefresh.isRefreshing = false
                when (it.status) {
                    Resource.Status.LOADING -> {
                        llLoading.visibility = VISIBLE
                        btnRetry.visibility = GONE
                    }

                    Resource.Status.SUCCESS -> {
                        llLoading.visibility = GONE
                        btnRetry.visibility = GONE
                        Log.e("response", "data ${it.data}")
                        indianNewsList.clear()
                        it.data?.let { it1 -> indianNewsList.addAll(it1) }
                        indianNewsAdapter.notifyDataSetChanged()
                    }

                    Resource.Status.ERROR -> {
                        llLoading.visibility = GONE
                        if (indianNewsList.isEmpty()) {
                            btnRetry.visibility = VISIBLE
                            context!!.toast(getString(R.string.no_internet_connection))
                        }
                    }
                }
            })
        }
    }

    private fun scheduleIndianNewsListWorkManager() {
        val data = HashMap<String, String>()
        data[SOURCE] = "in"

        CoroutineScope(Dispatchers.Main).launch {
            newsVM.getIndianNewsListUsingWorkManager(
                "scheduleGoogleNewsListWorkManager", data
            ).observe(this@IndianNewsListFragment, Observer {
                when (it.state) {
                    WorkInfo.State.RUNNING -> {
                        llLoading.visibility = VISIBLE
                        btnRetry.visibility = GONE
                    }

                    WorkInfo.State.SUCCEEDED -> {
                        getIndianNewsDetail(false)
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
