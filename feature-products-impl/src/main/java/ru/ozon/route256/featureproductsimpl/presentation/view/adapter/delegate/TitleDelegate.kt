package ru.ozon.route256.featureproductsimpl.presentation.view.adapter.delegate

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.ozon.route256.featureproductsimpl.databinding.ItemListTitleBinding
import ru.ozon.route256.featureproductsimpl.presentation.viewobject.TitleVO
import ru.ozon.route256.util.presentation.viewholder.ViewHolderModel

fun titleDelegate() =
    adapterDelegateViewBinding<TitleVO, ViewHolderModel, ItemListTitleBinding>(
        { inflater, parent ->
            ItemListTitleBinding.inflate(inflater, parent, false)
        }
    ) {
        bind {
            binding.title.text = item.title
        }
    }
