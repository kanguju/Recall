package com.example.for_the_mother.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MediaPagerAdapter(
    activity: FragmentActivity,
    private val items: List<MediaItem>
) : FragmentStateAdapter(activity) {
    override fun getItemCount() = items.size
    override fun createFragment(position: Int): Fragment {
        val item = items[position]
        return MediaPageFragment.newInstance(item.imageRes, item.videoRes)
    }
}
