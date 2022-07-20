package ru.ozon.route256.util.presentation.delegate

import ru.ozon.route256.util.databinding.ItemLoadingBinding
import ru.ozon.route256.util.presentation.viewholder.LoadingViewHolderModel

fun loadingViewHolderDelegate() =
    baseAdapterDelegate<LoadingViewHolderModel, ItemLoadingBinding>(
        { layoutInflater, parent ->
            ItemLoadingBinding.inflate(layoutInflater, parent, false)
        }
    ) {
    }
