package ru.ozon.route256.util.presentation.delegate

import android.widget.Button
import ru.ozon.route256.util.R
import ru.ozon.route256.util.databinding.ItemErrorBinding
import ru.ozon.route256.util.presentation.viewholder.ErrorViewHolderModel

fun errorViewHolderDelegate(onRetryClickListener: () -> Unit) =
    baseAdapterDelegate<ErrorViewHolderModel, ItemErrorBinding>(
        { layoutInflater, parent ->
            ItemErrorBinding.inflate(layoutInflater, parent, false)
        }
    ) {
        bind {
            with(binding) {
                val message = item.errorText
                if (message.isNotEmpty()) {
                    errorView.setErrorMessage(message)
                }

                val retryButton = binding.root.findViewById<Button>(R.id.btnRetry)
                retryButton.setOnClickListener {
                    onRetryClickListener()
                }
            }
        }
    }
