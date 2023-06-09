package com.jsontodos.jsonplaceholdertodos.presentation.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jsontodos.jsonplaceholdertodos.presentation.completed.CompletedFragment


class ViewStateAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun createFragment(position: Int): Fragment {
        // Hardcoded in this order, you'll want to use lists and make sure the titles match
        return if (position == 0) {
            HomeFragment()
        } else CompletedFragment()
    }

    override fun getItemCount(): Int {
        // Hardcoded, use lists
        return 2
    }
}