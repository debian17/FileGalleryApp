package com.debian17.filegalleryapp.app.ui.main

import android.os.Bundle
import com.debian17.filegalleryapp.R
import com.debian17.filegalleryapp.app.base.ui.BaseActivity
import com.debian17.filegalleryapp.app.ui.files.FilesFragment
import com.debian17.filegalleryapp.app.ui.navigation.BackPressedListener
import com.debian17.filegalleryapp.app.ui.navigation.MainNavigator
import com.debian17.filegalleryapp.app.ui.navigation.MainNavigatorOwner

class MainActivity : BaseActivity(), MainNavigatorOwner {

    private lateinit var mainNavigator: MainNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainNavigator = MainNavigator(supportFragmentManager)

        if (savedInstanceState == null) {
            val filesFragment = FilesFragment.newInstance()
            mainNavigator.add(filesFragment, false, FilesFragment.TAG)
        }

    }

    override fun onBackPressed() {
        val fragments = supportFragmentManager.fragments

        if (fragments.isEmpty()) {
            super.onBackPressed()
        } else {
            val fragment = fragments.last()

            var handled = false
            if (fragment != null && fragment is BackPressedListener) {
                handled = fragment.onBackPressed()
            }

            if (!handled) {
                super.onBackPressed()
            }
        }
    }

    override fun getMainNavigator(): MainNavigator {
        return mainNavigator
    }

}
