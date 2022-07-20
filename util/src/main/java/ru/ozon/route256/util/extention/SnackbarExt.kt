package ru.ozon.route256.util.extention

import android.view.View
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

fun Snackbar.action(@StringRes action: Int, listener: (View) -> Unit) {
    setAction(action, listener)
}
