package ru.ozon.route256.util.presentation.customview

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.ozon.route256.util.R
import ru.ozon.route256.util.databinding.ViewInCartButtonBinding

class InCartButtonView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defaultStyleAttr: Int = 0,
    defaultStyleRes: Int = 0
) : FrameLayout(context, attrs, defaultStyleAttr, defaultStyleRes) {

    private val binding by viewBinding<ViewInCartButtonBinding>()

    init {
        inflate(context, R.layout.view_in_cart_button, this)
    }

    fun loading() = with(binding) {
        inCartButton.text = ""
        buttonProgress.isVisible = true
    }

    fun changeState(isInCart: Boolean) = with(binding) {
        buttonProgress.isVisible = false
        val addInCartButtonText = context.getString(R.string.add_in_cart)
        val inCartButtonText = context.getString(R.string.in_cart)

        if (isInCart) {
            inCartButton.text = inCartButtonText
            inCartButton.backgroundTintList =
                ContextCompat.getColorStateList(context, R.color.teal_200)
        } else {
            inCartButton.text = addInCartButtonText
            inCartButton.backgroundTintList =
                ContextCompat.getColorStateList(context, R.color.grey2)
        }
    }
}
