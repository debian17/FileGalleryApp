package com.debian17.filegalleryapp.app.base.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import com.debian17.filegalleryapp.app.ui.navigation.BackPressedListener
import com.debian17.filegalleryapp.app.ui.navigation.MainNavigator
import com.debian17.filegalleryapp.app.ui.navigation.MainNavigatorOwner

abstract class BaseFragment : Fragment(), BackPressedListener {

    protected lateinit var mainNavigator: MainNavigator

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.setBackgroundColor(Color.WHITE)
        mainNavigator = (activity!! as MainNavigatorOwner).getMainNavigator()
    }

    override fun onBackPressed(): Boolean {
        return false
    }

}