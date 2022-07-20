package ru.ozon.route256.featureaddproductimpl.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.ozon.route256.core.factory.viewModelCreator
import ru.ozon.route256.core.presentation.BaseFragment
import ru.ozon.route256.featureaddproductapi.AddProductNavigationApi
import ru.ozon.route256.featureaddproductimpl.R
import ru.ozon.route256.featureaddproductimpl.databinding.FragmentAddProductBinding
import ru.ozon.route256.featureaddproductimpl.di.FeatureAddProductComponent
import ru.ozon.route256.featureaddproductimpl.domain.interactor.AddProductInteractor
import ru.ozon.route256.featureaddproductimpl.presentation.viewmodel.AddProductViewModel
import ru.ozon.route256.featureaddproductimpl.util.AddProductFieldType
import javax.inject.Inject

class AddProductFragment : BaseFragment(R.layout.fragment_add_product) {

    companion object {
        fun newInstance() = AddProductFragment()
    }

    @Inject
    lateinit var addProductInteractor: AddProductInteractor

    @Inject
    lateinit var addProductNavigationApi: AddProductNavigationApi

    private val binding: FragmentAddProductBinding by viewBinding()
    private val viewModel: AddProductViewModel by viewModelCreator {
        AddProductViewModel(addProductInteractor)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        FeatureAddProductComponent.featureAddProductComponent?.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.isActivateButtonState.observe(viewLifecycleOwner, ::handleResult)
        initViews()
    }

    override fun onPause() {
        if (isRemoving) {
            if (addProductNavigationApi.isFeatureClosed(this)) {
                FeatureAddProductComponent.resetComponent()
            }
        }
        super.onPause()
    }

    override fun onBackPressed() {
        parentFragmentManager.popBackStack()
    }

    private fun initViews() = with(binding) {
        etImage.doAfterTextChanged {
            viewModel.fieldEdit(
                newValue = it.toString(),
                fieldType = AddProductFieldType.IMAGE
            )
        }
        etName.doAfterTextChanged {
            viewModel.fieldEdit(
                newValue = it.toString(),
                fieldType = AddProductFieldType.NAME
            )
        }
        etPrice.doAfterTextChanged {
            viewModel.fieldEdit(
                newValue = it.toString(),
                fieldType = AddProductFieldType.PRICE
            )
        }

        addButton.setOnClickListener {
            viewModel.addProduct()

            activity?.application?.let { it1 ->
                addProductNavigationApi.navigateToProductList(
                    fragment = this@AddProductFragment,
                    it1
                )
            }
        }
    }

    private fun handleResult(state: Boolean) = with(binding) {
        addButton.isEnabled = state
    }
}
