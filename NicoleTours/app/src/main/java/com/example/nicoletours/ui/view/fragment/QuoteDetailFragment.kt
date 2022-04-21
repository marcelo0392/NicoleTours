package com.example.nicoletours.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nicoletours.databinding.FragmentQuoteDetailBinding

class QuoteDetailFragment : Fragment() {

    private var _binding:FragmentQuoteDetailBinding?=null
    private val binding get() = _binding!!

    companion object {
        private const val ARG_OBJECT = "objeto"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentQuoteDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            binding.textView2.text = "Fragmento " + getInt(ARG_OBJECT).toString()
        }
    }
}