package com.debian17.filegalleryapp.app.ui.files.image


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper

import com.debian17.filegalleryapp.R
import com.debian17.filegalleryapp.app.base.ui.BaseFragment
import com.debian17.filegalleryapp.app.ui.files.FileDiffCallback
import kotlinx.android.synthetic.main.fragment_image.*
import java.io.File


class ImageFragment : BaseFragment() {

    companion object {
        private const val PATHS = "paths"
        private const val START_POSITION = "startPosition"
        const val TAG = "ImageFragment"
        fun newInstance(paths: Array<String>, startPosition: Int): ImageFragment {
            return ImageFragment().apply {
                arguments = Bundle().apply {
                    putStringArray(PATHS, paths)
                    putInt(START_POSITION, startPosition)
                }
            }
        }
    }

    private lateinit var adapter: ImageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity!!.window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val files = arguments!!.getStringArray(PATHS)!!.map { File(it) }
        val startPosition = arguments!!.getInt(START_POSITION, 0)

        adapter = ImageAdapter(context!!)

        rvImages.layoutManager =
            LinearLayoutManager(context!!, LinearLayoutManager.HORIZONTAL, false)

        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(rvImages)

        rvImages.adapter = adapter

        val fileDiffCallback = FileDiffCallback(adapter.getItems(), files)
        adapter.replaceItems(files, fileDiffCallback)

        rvImages.scrollToPosition(startPosition)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        activity!!.window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }


}
