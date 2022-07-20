package ru.ozon.route256.util.presentation.customview

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.ozon.route256.util.R
import ru.ozon.route256.util.databinding.LayoutErrorViewBinding

class ErrorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding by viewBinding<LayoutErrorViewBinding>()

    init {
        inflate(context, R.layout.layout_error_view, this)
        orientation = VERTICAL
        gravity = Gravity.CENTER
    }

    fun setErrorMessage(text: String) {
        binding.tvErrorMessage.text = text
    }
}
