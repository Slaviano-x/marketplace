package ru.ozon.route256.featureproductsimpl.presentation.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import ru.ozon.route256.featureproductsimpl.presentation.view.adapter.delegate.productsDelegate
import ru.ozon.route256.featureproductsimpl.presentation.view.adapter.delegate.titleDelegate
import ru.ozon.route256.util.presentation.delegate.errorViewHolderDelegate
import ru.ozon.route256.util.presentation.delegate.loadingViewHolderDelegate
import ru.ozon.route256.util.presentation.viewholder.ViewHolderModel

class ProductsAdapter(
    onProductClick: (guid: String) -> Unit,
    onRetryClickListener: () -> Unit,
    onInCartClick: (guid: String, position: Int) -> Unit,
) : AsyncListDifferDelegationAdapter<ViewHolderModel>(Companion) {

    init {
        delegatesManager.apply {
            addDelegate(productsDelegate(onProductClick, onInCartClick))
            addDelegate(titleDelegate())
            addDelegate(loadingViewHolderDelegate())
            addDelegate(errorViewHolderDelegate(onRetryClickListener))
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
