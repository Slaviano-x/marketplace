package ru.ozon.route256.featureproductsimpl.presentation.view.adapter.delegate

import androidx.recyclerview.widget.LinearLayoutManager
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.ozon.route256.featureproductsimpl.R
import ru.ozon.route256.featureproductsimpl.databinding.ItemProductListBinding
import ru.ozon.route256.featureproductsimpl.presentation.view.adapter.ImagesAdapter
import ru.ozon.route256.featureproductsimpl.presentation.viewobject.ImageVO
import ru.ozon.route256.featureproductsimpl.presentation.viewobject.ProductVO
import ru.ozon.route256.util.presentation.viewholder.ViewHolderModel

fun productsDelegate(
    onProductClick: (guid: String) -> Unit,
    onInCartClick: (guid: String, position: Int) -> Unit,
) =
    adapterDelegateViewBinding<ProductVO, ViewHolderModel, ItemProductListBinding>(
        { inflater, parent ->
            ItemProductListBinding.inflate(inflater, parent, false)
        }
    ) {
        val adapter = ImagesAdapter()
        binding.imageRV.adapter = adapter
        binding.imageRV.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        bind {
            with(binding) {
                adapter.items = item.images.map { ImageVO(it) }

                nameTV.text = item.name
                priceTV.text = getString(R.string.currency, item.price)
                itemGroupView.setOnClickListener {
                    onProductClick(item.guid)
                }
                ratingView.rating = item.rating
                ratingValue.text = item.rating.toString()
                val inCartButton = inCartButtonGroupView.getChildAt(0)
                inCartButtonGroupView.changeState(item.isInCart)
                inCartButton.setOnClickListener {
                    inCartButtonGroupView.loading()
                    onInCartClick(item.guid, layoutPosition)
                }
            }
        }
    }
