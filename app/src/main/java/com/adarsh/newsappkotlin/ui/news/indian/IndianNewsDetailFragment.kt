package com.adarsh.newsappkotlin.ui.news.indian


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs

import com.adarsh.newsappkotlin.R
import com.adarsh.newsappkotlin.databinding.FragmentIndianNewsDetailBinding

/**
 * A simple [Fragment] subclass.
 */
class IndianNewsDetailFragment : Fragment() {

    private val argument: IndianNewsDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val indianNewsDetailBinding =
            FragmentIndianNewsDetailBinding.inflate(inflater, container, false)
        indianNewsDetailBinding.indiaNewsDetail = argument.indianNewsDetail
        indianNewsDetailBinding.ivBack.setOnClickListener {
            it.findNavController().navigateUp()
        }
        return indianNewsDetailBinding.root
    }


}
