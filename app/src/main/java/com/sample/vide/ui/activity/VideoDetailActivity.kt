package com.sample.vide.ui.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.sample.vide.R
import com.sample.vide.databinding.ActivityVideoDetailBinding
import com.sample.vide.data.model.VideoDto

class VideoDetailActivity : AppCompatActivity() {

    private var _binding: ActivityVideoDetailBinding? = null
    private val binding
        get() = _binding

    private var videoDetail: VideoDto? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = DataBindingUtil.setContentView(this, R.layout.activity_video_detail)

        initToolbar()

        videoDetail = intent.getParcelableExtra<VideoDto>("data") as? VideoDto

        videoDetail?.let { fetchData(it) }

    }

    private fun fetchData(videoDetail: VideoDto) {
        _binding?.data = videoDetail
    }

    private fun initToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}