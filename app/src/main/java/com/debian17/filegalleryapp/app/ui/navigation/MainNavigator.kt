package com.debian17.filegalleryapp.app.ui.navigation

import androidx.fragment.app.FragmentManager
import com.debian17.filegalleryapp.R
import com.debian17.filegalleryapp.app.base.ui.BaseFragment

class MainNavigator(private val fragmentManager: FragmentManager) {

    fun add(fragment: BaseFragment, addToBackStack: Boolean, tag: String) {
        val transaction = fragmentManager.beginTransaction()
            .add(R.id.flMainontainer, fragment, tag)
        if (addToBackStack) {
            transaction.addToBackStack(tag)
        }
        transaction.commit()
    }

    fun back() {
        fragmentManager.popBackStack()
    }

}