package ru.ozon.route256.featureproductsimpl.presentation.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import ru.ozon.route256.featureproductsimpl.presentation.view.adapter.delegate.imageDelegate
import ru.ozon.route256.util.presentation.viewholder.ViewHolderModel

class ImagesAdapter : AsyncListDifferDelegationAdapter<ViewHolderModel>(Companion) {

    init {
        delegatesManager.apply {
            addDelegate(imageDelegate())
        }
    }

    private companion object : DiffUtil.ItemCallback<ViewHolderModel>() {
        override fun areItemsTheSame(
            oldItem: ViewHolderModel,
            newItem: ViewHolderModel
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: ViewHolderModel,
            newItem: ViewHolderModel
        ): Boolean {
            return areItemsTheSame(oldItem, newItem)
        }
    }
}
