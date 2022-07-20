package ru.ozon.route256.util.presentation.delegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.dsl.AdapterDelegateViewBindingViewHolder
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.ozon.route256.util.presentation.viewholder.ViewHolderModel

inline fun <reified I : ViewHolderModel, V : ViewBinding> baseAdapterDelegate(
    noinline viewBinding: (layoutInflater: LayoutInflater, parent: ViewGroup) -> V,
    noinline on: (item: ViewHolderModel, items: List<ViewHolderModel>, position: Int) -> Boolean = { item, _, _ -> item is I },
    noinline layoutInflater: (parent: ViewGroup) -> LayoutInflater = { parent ->
        LayoutInflater.from(
            parent.context
        )
    },
    noinline block: AdapterDelegateViewBindingViewHolder<I, V>.() -> Unit
): AdapterDelegate<List<ViewHolderModel>> = adapterDelegateViewBinding(
    viewBinding = viewBinding,
    on = on,
    block = block,
    layoutInflater = layoutInflater
)
