package com.adarsh.newsappkotlin.ui.news.google


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.adarsh.newsappkotlin.databinding.FragmentGoogleNewsDetailBinding

/**
 * A simple [Fragment] subclass.
 */
class GoogleNewsDetailFragment : Fragment() {

    private val argument: GoogleNewsDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentNewsDetailBinding =
            FragmentGoogleNewsDetailBinding.inflate(inflater, container, false)
        fragmentNewsDetailBinding.googleNewsDetail = argument.googleNewsItem
        fragmentNewsDetailBinding.ivBack.setOnClickListener {
            it.findNavController().navigateUp()
        }
        return fragmentNewsDetailBinding.root
    }
}
