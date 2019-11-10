package com.adarsh.newsappkotlin.ui.news


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.adarsh.newsappkotlin.R
import com.adarsh.newsappkotlin.databinding.FragmentNewsOptionBinding
import com.adarsh.newsappkotlin.di.InjectableInterface
import com.adarsh.newsappkotlin.ui.vm.OptionSharedVM
import kotlinx.android.synthetic.main.fragment_news_option.*

/**
 * A simple [Fragment] subclass.
 */
class NewsOptionFragment : Fragment(), InjectableInterface, View.OnClickListener {

    private lateinit var optionSharedVM: OptionSharedVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentNewsOptionBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        optionSharedVM = ViewModelProviders.of(activity!!).get(OptionSharedVM::class.java)

        cvGoogleNews.setOnClickListener(this)
        cvIndianNews.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.cvGoogleNews -> {
                optionSharedVM.setOption(getString(R.string.google_news))
                view.findNavController()
                    .navigate(R.id.action_newsOptionFragment_to_newsListFragment)
            }

            R.id.cvIndianNews -> {
                optionSharedVM.setOption(getString(R.string.indian_news))
                view.findNavController()
                    .navigate(R.id.action_newsOptionFragment_to_indianNewsListFragment)
            }
        }
    }
}
