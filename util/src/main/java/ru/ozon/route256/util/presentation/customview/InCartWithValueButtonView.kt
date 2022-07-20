package ru.ozon.route256.util.presentation.customview

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.button.MaterialButton
import ru.ozon.route256.util.R
import ru.ozon.route256.util.databinding.ViewInCartWithValueButtonBinding

class InCartWithValueButtonView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defaultStyleAttr: Int = 0,
    defaultStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defaultStyleAttr, defaultStyleRes) {

    private val binding by viewBinding<ViewInCartWithValueButtonBinding>()

    init {
        inflate(context, R.layout.view_in_cart_with_value_button, this)
    }

    fun getValue(): Int = binding.valueProduct.text.toString().toInt()
    fun setValue(count: Int) {
        binding.valueProduct.text = count.toString()
    }

    fun getInCartButton(): MaterialButton = binding.inCartButton
    fun getRemoveButton(): ImageButton = binding.removeBtn
    fun getAddButton(): ImageButton = binding.addBtn

    fun loading() = with(binding) {
        inCartButton.text = ""
        buttonProgress.isVisible = true
    }

    fun changeButtonState(isInCart: Boolean) = with(binding) {
        buttonProgress.isVisible = false
        val addInCartButtonText = context.getString(R.string.add_in_cart)
        val inCartButtonText = context.getString(R.string.in_cart)

        if (isInCart) {
            inCartButton.text = inCartButtonText
            inCartButton.backgroundTintList =
                ContextCompat.getColorStateList(context, R.color.teal_200)
            isVisibleValue(true)
        } else {
            inCartButton.text = addInCartButtonText
            inCartButton.backgroundTintList =
                ContextCompat.getColorStateList(context, R.color.grey2)
            isVisibleValue(false)
        }
    }

    fun isVisibleValue(isVisible: Boolean) = with(binding) {
        removeBtn.isVisible = isVisible
        valueProduct.isVisible = isVisible
        addBtn.isVisible = isVisible
    }
}
