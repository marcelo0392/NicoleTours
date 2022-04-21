package com.example.nicoletours

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.nicoletours.ui.view.fragment.QuoteDetailFragment
import com.example.nicoletours.ui.view.fragment.QuoteFragment

class ViewPageFragmentAdapter(fa:FragmentActivity):FragmentStateAdapter(fa) {

    companion object{
        private const val ARG_OBJECT = "objeto"
    }

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {

        val fragment = when(position){
            0->{
                QuoteFragment()
            }
            else ->{
                QuoteDetailFragment()
            }
        }

        fragment.arguments = Bundle().apply {
            putInt(ARG_OBJECT, position +1)
        }
        return fragment
    }
}