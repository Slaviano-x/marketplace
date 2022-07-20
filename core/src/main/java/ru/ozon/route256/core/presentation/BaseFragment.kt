package ru.ozon.route256.core.presentation

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment(
    @LayoutRes layoutResId: Int
) : Fragment(layoutResId) {

    open fun onBackPressed() = Unit
}
