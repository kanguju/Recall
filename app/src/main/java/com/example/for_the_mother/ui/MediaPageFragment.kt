package com.example.for_the_mother.ui

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.MediaController
import android.widget.VideoView
import androidx.fragment.app.Fragment
import com.example.for_the_mother.R

class MediaPageFragment : Fragment() {

    companion object {
        private const val ARG_IMG = "arg_img"
        private const val ARG_VID = "arg_vid"

        fun newInstance(imageRes: Int, videoRes: Int) =
            MediaPageFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_IMG, imageRes)
                    putInt(ARG_VID, videoRes)
                }
            }
    }

    private var imageRes: Int = 0
    private var videoRes: Int = 0

    private lateinit var imageView: ImageView
    private lateinit var videoView: VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            imageRes = it.getInt(ARG_IMG)
            videoRes = it.getInt(ARG_VID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val v = inflater.inflate(R.layout.fragment_media_page, container, false)
        imageView = v.findViewById(R.id.image)
        videoView = v.findViewById(R.id.video)

        // 이미지 표시
        imageView.setImageResource(imageRes)
        imageView.visibility = View.VISIBLE
        videoView.visibility = View.GONE

        // 이미지 클릭 시 영상 재생
        imageView.setOnClickListener { startVideo() }

        return v
    }

    /** 영상 시작 */
    private fun startVideo() {
        imageView.visibility = View.GONE
        videoView.visibility = View.VISIBLE

        val uri = Uri.parse("android.resource://${requireContext().packageName}/$videoRes")
        videoView.apply {
            setVideoURI(uri)
            setMediaController(MediaController(requireContext()).also { it.setAnchorView(this) })

            // ✅ 영상이 끝나면 다시 이미지로 돌아가도록
            setOnCompletionListener {
                resetToImage()
            }

            start()
        }
    }

    /** ✅ 영상 정지 + 이미지 복귀 (공통 함수) */
    private fun resetToImage() {
        try { videoView.stopPlayback() } catch (_: Exception) {}
        videoView.visibility = View.GONE
        imageView.visibility = View.VISIBLE
    }

    /** ✅ 슬라이드 이동 시에도 자동 복귀 */
    override fun onPause() {
        super.onPause()
        if (this::videoView.isInitialized && videoView.isPlaying) {
            resetToImage()
        }
    }
}
