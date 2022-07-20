package ru.ozon.route256.featurepdpimpl.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.view.isGone
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import io.reactivex.disposables.Disposable
import ru.ozon.route256.core.factory.viewModelCreator
import ru.ozon.route256.core.presentation.BaseFragment
import ru.ozon.route256.featurepdpapi.PDPNavigationApi
import ru.ozon.route256.featurepdpimpl.R
import ru.ozon.route256.featurepdpimpl.databinding.FragmentPdpBinding
import ru.ozon.route256.featurepdpimpl.di.PDPFeatureComponent
import ru.ozon.route256.featurepdpimpl.domain.interactor.PDPInteractor
import ru.ozon.route256.featurepdpimpl.domain.model.ProductDetailPageQueryState
import ru.ozon.route256.featurepdpimpl.presentation.viewmodel.PDPViewModel
import ru.ozon.route256.featurepdpimpl.presentation.viewobject.ProductVO
import javax.inject.Inject

private const val GUID = "guid"

class PDPFragment : BaseFragment(R.layout.fragment_pdp) {

    companion object {
        fun newInstance(guid: String) =
            PDPFragment().apply {
                arguments = Bundle().apply {
                    putString(GUID, guid)
                }
            }
    }

    @Inject
    lateinit var pdpInteractor: PDPInteractor

    @Inject
    lateinit var pdpNavigationApi: PDPNavigationApi

    private val binding: FragmentPdpBinding by viewBinding()
    private val viewModel: PDPViewModel by viewModelCreator {
        PDPViewModel(pdpInteractor)
    }
    private var guid: String? = null
    private var disposable: Disposable? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        PDPFeatureComponent.pdpFeatureComponent?.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            guid = it.getString(GUID)
        }
        initButtons()
        viewModel.pdpLD.observe(viewLifecycleOwner, ::handleResult)
        viewModel.setProductId(guid)
        disposable = viewModel.loadData()
    }

    override fun onPause() {
        if (isRemoving) {
            if (pdpNavigationApi.isFeatureClosed(this)) {
                PDPFeatureComponent.resetComponent()
            }
        }
        super.onPause()
        disposable?.dispose()
    }

    override fun onBackPressed() {
        parentFragmentManager.popBackStack()
    }

    private fun handleResult(state: ProductDetailPageQueryState?) {
        if (state != null) {
            when (state) {
                is ProductDetailPageQueryState.Loading -> handleLoading()
                is ProductDetailPageQueryState.Success -> setUI(state.product)
                else -> setError()
            }
        }
    }

    private fun handleLoading() = with(binding) {
        progressBar.isVisible = true
        errorView.isVisible = false
    }

    private fun setError() = with(binding) {
        productIV.isGone = true
        nameTV.isGone = true
        priceTV.isGone = true
        ratingView.isGone = true
        inCartBtn.isGone = true
        progressBar.isVisible = false
        errorView.isVisible = true
    }

    private fun setUI(pdpVO: ProductVO) = with(binding) {
        binding.progressBar.isVisible = false
        productIV.load(pdpVO.images[0]) {
            build()
        }
        nameTV.text = pdpVO.name
        priceTV.text = getString(R.string.currency, pdpVO.price)
        ratingView.isVisible = true
        inCartBtn.isVisible = true
        if (pdpVO.count > 0) {
            inCartBtn.setValue(pdpVO.count)
            inCartBtn.changeButtonState(true)
            inCartBtn.isVisibleValue(true)
        }
        ratingView.rating = pdpVO.rating.toFloat()
        ratingValue.text = pdpVO.rating.toString()
        if (pdpVO.description.isEmpty()) {
            descriptionHeader.isGone = true
            description.isGone = true
        } else {
            descriptionHeader.isVisible = true
            description.isVisible = true
            description.text = pdpVO.description
        }
    }

    private fun initButtons() = with(binding) {
        initErrorRetryButton()
        initInCartButton()
        initRemoveFromCartButton()
        initAddInCartButton()
    }

    private fun initAddInCartButton() = with(binding) {
        val addBtn = inCartBtn.getAddButton()
        addBtn.setOnClickListener {
            var lastValue = inCartBtn.getValue()
            lastValue++
            inCartBtn.setValue(lastValue)
            viewModel.changeCount(lastValue, guid)
        }
    }

    private fun initRemoveFromCartButton() = with(binding) {
        val removeBtn = inCartBtn.getRemoveButton()
        removeBtn.setOnClickListener {
            var lastValue = inCartBtn.getValue()
            lastValue--
            if (lastValue == 0) {
                inCartBtn.isVisibleValue(false)
                inCartBtn.changeButtonState(false)
            }
            inCartBtn.setValue(lastValue)
            viewModel.changeCount(lastValue, guid)
        }
    }

    private fun initInCartButton() = with(binding) {
        val inCartButton = inCartBtn.getInCartButton()
        inCartButton.setOnClickListener {
            var lastValue = inCartBtn.getValue()
            if (lastValue > 0) {
                lastValue = 0
                inCartBtn.changeButtonState(false)
                inCartBtn.setValue(lastValue)
                inCartBtn.isVisibleValue(false)
            } else {
                lastValue++
                inCartBtn.changeButtonState(true)
                inCartBtn.setValue(lastValue)
                inCartBtn.isVisibleValue(true)
            }
            viewModel.changeCount(lastValue, guid)
        }
    }

    private fun initErrorRetryButton() {
        val retryButton =
            binding.errorView.rootView.findViewById<Button>(ru.ozon.route256.util.R.id.btnRetry)
        retryButton.setOnClickListener {
            viewModel.loadData()
        }
    }
}
