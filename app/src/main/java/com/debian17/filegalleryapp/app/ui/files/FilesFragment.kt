package com.debian17.filegalleryapp.app.ui.files


import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.view.*
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator

import com.debian17.filegalleryapp.R
import com.debian17.filegalleryapp.app.base.longSnackbar
import com.debian17.filegalleryapp.app.base.sortedFiles
import com.debian17.filegalleryapp.app.base.ui.BaseFragment
import com.debian17.filegalleryapp.app.ui.files.image.ImageFragment
import com.debian17.filegalleryapp.app.ui.navigation.BackPressedListener
import com.debian17.filegalleryapp.app.util.FileUtils
import com.debian17.filegalleryapp.app.util.ImageExtension
import kotlinx.android.synthetic.main.fragment_files.*
import java.io.File

class FilesFragment : BaseFragment(), FileAdapter.FileCLickListener,
    BackPressedListener {

    companion object {
        private const val REQUEST_CODE_WRITE_STORAGE = 0
        private const val EXIT_DELAY = 3000L
        const val TAG = "FilesFragmentTag"
        fun newInstance(): FilesFragment {
            return FilesFragment()
        }
    }

    private val mainHandler = Handler()

    private lateinit var curDirectory: File

    private lateinit var adapter: FileAdapter

    private var pressedToExit = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_files, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecycler()

        val result =
            ContextCompat.checkSelfPermission(context!!, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (result == PackageManager.PERMISSION_DENIED) {
            requestPermissions(
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                REQUEST_CODE_WRITE_STORAGE
            )
        } else {
            showRootFiles()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CODE_WRITE_STORAGE && grantResults.isNotEmpty()) {
            val result = grantResults[0]
            if (result == PackageManager.PERMISSION_GRANTED) {

                showRootFiles()

            } else {
                Toast.makeText(context!!, getString(R.string.permissions_error), Toast.LENGTH_LONG)
                    .show()
                activity!!.finish()
            }
        }
    }

    private fun setupRecycler() {
        adapter = FileAdapter(context!!, this)
        rvFiles.layoutManager = LinearLayoutManager(context)

        val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        divider.setDrawable(ContextCompat.getDrawable(context!!, R.drawable.item_dvider)!!)
        rvFiles.addItemDecoration(divider)

        (rvFiles.itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = false

        rvFiles.adapter = adapter
    }

    private fun showRootFiles() {
        if (FileUtils.isExternalStorageMounted()) {
            curDirectory = Environment.getExternalStorageDirectory()
            tvCurDirectory.text = curDirectory.path

            val files = curDirectory.sortedFiles()
            val filesDiffCallback = FileDiffCallback(adapter.getItems(), files)

            adapter.replaceItems(files, filesDiffCallback)
        }
    }

    override fun onFileClick(file: File) {
        if (file.isDirectory) {

            curDirectory = file

            tvCurDirectory.text = curDirectory.path

            val files = file.sortedFiles()
            val filesDiffCallback = FileDiffCallback(adapter.getItems(), files)
            adapter.replaceItems(files, filesDiffCallback)
        } else {

            val extension = file.extension
            val exts = ImageExtension.getExtensions()
            if (extension in exts) {
                val path = file.path
                val pathsOfImages = curDirectory.sortedFiles()
                    .filter { it.extension in exts }
                    .map { it.path }
                    .toTypedArray()

                val position = pathsOfImages.indexOf(path)

                val imageFragment = ImageFragment.newInstance(pathsOfImages, position)
                mainNavigator.add(imageFragment, true, ImageFragment.TAG)
            }

        }
    }

    override fun onBackPressed(): Boolean {
        if (curDirectory == Environment.getExternalStorageDirectory()) {

            onPressedToExit()

        } else {
            curDirectory = curDirectory.parentFile

            val files = curDirectory.sortedFiles()
            val filesDiffCallback = FileDiffCallback(adapter.getItems(), files)
            adapter.replaceItems(files, filesDiffCallback)

            tvCurDirectory.text = curDirectory.path
        }
        return true
    }

    private fun onPressedToExit() {
        if (pressedToExit) {
            activity!!.finish()
        } else {
            pressedToExit = true

            mainHandler.postDelayed({
                pressedToExit = false
            }, EXIT_DELAY)

            view?.longSnackbar(getString(R.string.press_again_to_exit))
        }
    }


}
