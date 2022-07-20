package ru.ozon.route256.featureaddproductimpl.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.ozon.route256.featureaddproductimpl.domain.interactor.AddProductInteractor
import ru.ozon.route256.featureaddproductimpl.util.AddProductFieldType
import javax.inject.Inject

class AddProductViewModel @Inject constructor(
    private val interactor: AddProductInteractor
) : ViewModel() {

    val isActivateButtonState = MutableLiveData<Boolean>()
    private var etState = EditTextState()

    fun addProduct() {
        interactor.addProduct(etState.name, etState.price, etState.image)
    }

    fun fieldEdit(newValue: String, fieldType: AddProductFieldType) {
        etState = when (fieldType) {
            AddProductFieldType.IMAGE -> etState.copy(image = newValue)
            AddProductFieldType.NAME -> etState.copy(name = newValue)
            AddProductFieldType.PRICE -> etState.copy(price = newValue)
        }
        isActivateButtonState.postValue(etState.name.isNotEmpty() && etState.price.isNotEmpty())
    }
}

private data class EditTextState(
    val image: String = "",
    val name: String = "",
    val price: String = ""
)
