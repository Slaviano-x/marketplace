package ru.ozon.route256.util.extention

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

inline fun Fragment.showSnackbar(
    @StringRes messageRes: Int? = null,
    messageStr: String? = null,
    length: Int = Snackbar.LENGTH_LONG,
    act: Snackbar.() -> Unit = {
        action(android.R.string.ok) {
            this.dismiss()
        }
    },
) {
    val message = (messageStr ?: messageRes?.let { getString(it) }) as String
    val snack = Snackbar.make(requireView(), message, length)
    snack.act()
    snack.show()
}
