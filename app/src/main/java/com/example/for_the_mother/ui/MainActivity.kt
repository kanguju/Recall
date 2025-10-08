package com.example.for_the_mother.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.for_the_mother.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val items = listOf(
            MediaItem(R.drawable.family_photo_1, R.raw.family_photo_1_video),
            MediaItem(R.drawable.family_photo_2, R.raw.family_photo_2_video),
            MediaItem(R.drawable.family_photo_3, R.raw.family_photo_3_video),
            MediaItem(R.drawable.family_photo_4, R.raw.family_photo_4_video),
            MediaItem(R.drawable.family_photo_5, R.raw.family_photo_5_video)
        )

        val pager = findViewById<ViewPager2>(R.id.pager)
        pager.adapter = MediaPagerAdapter(this, items)
        // pager.orientation = ViewPager2.ORIENTATION_HORIZONTAL // 기본값
    }
}
