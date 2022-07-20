package ru.ozon.route256.featureproductsimpl.presentation.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import io.reactivex.disposables.Disposable
import ru.ozon.route256.core.factory.viewModelCreator
import ru.ozon.route256.core.presentation.BaseFragment
import ru.ozon.route256.coredataapi.ResourceProvider
import ru.ozon.route256.featureproductsapi.ProductNavigationApi
import ru.ozon.route256.featureproductsimpl.R
import ru.ozon.route256.featureproductsimpl.databinding.FragmentProductsBinding
import ru.ozon.route256.featureproductsimpl.di.FeatureProductsComponent
import ru.ozon.route256.featureproductsimpl.domain.interactor.ProductsInteractor
import ru.ozon.route256.featureproductsimpl.domain.model.ProductsQueryState
import ru.ozon.route256.featureproductsimpl.presentation.view.adapter.ProductsAdapter
import ru.ozon.route256.featureproductsimpl.presentation.viewmodel.ProductsViewModel
import ru.ozon.route256.util.presentation.viewholder.ErrorViewHolderModel
import ru.ozon.route256.util.presentation.viewholder.LoadingViewHolderModel
import ru.ozon.route256.workmanagerapi.WorkManagerApi
import javax.inject.Inject

class ProductsFragment : BaseFragment(R.layout.fragment_products) {
    @Inject
    lateinit var productsInteractor: ProductsInteractor

    @Inject
    lateinit var productNavigationApi: ProductNavigationApi

    @Inject
    lateinit var workManagerApi: WorkManagerApi

    @Inject
    lateinit var resourceProvider: ResourceProvider

    private var dataDisposable: Disposable? = null
    private var timerDisposable: Disposable? = null
    private var inCartDisposable: Disposable? = null

    private val binding: FragmentProductsBinding by viewBinding()
    private val viewModel: ProductsViewModel by viewModelCreator {
        ProductsViewModel(productsInteractor, workManagerApi.getProductsLoader(), resourceProvider)
    }

    private val adapter by lazy {
        ProductsAdapter(
            ::onProductClick,
            ::onRetryClick,
            ::onInCartClick
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        FeatureProductsComponent.featureProductsComponent?.inject(this)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        viewModel.updateCart()
        super.onResume()
        adapter.notifyDataSetChanged()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.uiState.observe(viewLifecycleOwner, ::handleResult)
        dataDisposable = viewModel.observeData()
        timerDisposable = viewModel.startPeriodicUpdateData()
        initViews()
    }

    override fun onPause() {
        if (isRemoving) {
            if (productNavigationApi.isFeatureClosed(this)) {
                FeatureProductsComponent.resetComponent()
            }
        }
        super.onPause()
        dataDisposable?.dispose()
        timerDisposable?.dispose()
        inCartDisposable?.dispose()
    }

    private fun handleResult(state: ProductsQueryState) {
        val uiState = when (state) {
            is ProductsQueryState.Loading -> {
                handleLoading()
                listOf(LoadingViewHolderModel())
            }
            is ProductsQueryState.Success -> {
                handleResult()
                state.productList
            }
            else -> {
                handleResult()
                listOf(ErrorViewHolderModel())
            }
        }

        adapter.items = uiState
    }

    private fun handleResult() {
        binding.addProductFAB.isVisible = true
    }

    private fun handleLoading() {
        binding.addProductFAB.isVisible = false
    }

    override fun onBackPressed() {
        requireActivity().finishAffinity()
    }

    private fun initViews() {
        initRecycler()
        initButtons()
    }

    private fun initRecycler() = with(binding) {
        productRecycler.adapter = adapter
        productRecycler.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initButtons() {
        binding.addProductFAB.setOnClickListener {
            productNavigationApi.navigateToAddProduct(fragment = this)
        }
    }

    private fun onProductClick(guid: String) {
        productNavigationApi.navigateToPDP(fragment = this, guid)
    }

    private fun onRetryClick() {
        viewModel.tryLoadData()
    }

    private fun onInCartClick(guid: String, position: Int) {
        // Это сделано для моделирования загрузки, иначе очень быстро происходит и загрузки не видно
        inCartDisposable = viewModel.inCartClick(guid)
        binding.productRecycler.postDelayed({
            adapter.notifyItemChanged(position)
        }, 500)
    }
}
