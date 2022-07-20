package ru.ozon.route256.featureproductsimpl.presentation.view.adapter.delegate

import coil.load
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.ozon.route256.featureproductsimpl.databinding.ItemImageBinding
import ru.ozon.route256.featureproductsimpl.presentation.viewobject.ImageVO
import ru.ozon.route256.util.presentation.viewholder.ViewHolderModel

fun imageDelegate() =
    adapterDelegateViewBinding<ImageVO, ViewHolderModel, ItemImageBinding>(
        { inflater, parent ->
            ItemImageBinding.inflate(inflater, parent, false)
        }
    ) {
        bind {
            binding.imageIV.load(item.image) {
                build()
            }
        }
    }
