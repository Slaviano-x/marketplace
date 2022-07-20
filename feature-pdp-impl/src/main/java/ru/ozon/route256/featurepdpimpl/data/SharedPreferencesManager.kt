package ru.ozon.route256.featurepdpimpl.data

import android.content.SharedPreferences
import com.squareup.moshi.Moshi
import ru.ozon.route256.corenetworkapi.extension.adapterInCartList
import ru.ozon.route256.corenetworkapi.model.InCartModel
import ru.ozon.route256.util.const.PreferencesConst
import javax.inject.Inject

class SharedPreferencesManager @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val moshi: Moshi
) {
    private val editor = sharedPreferences.edit()

    fun getCart(): List<InCartModel>? = getInCartList()

    fun changeValue(lastValue: Int, guid: String?) {
        var inCartList: MutableList<InCartModel>? = getInCartList()?.toMutableList()
        val inCartCount = inCartList?.find { it.guid == guid }?.count

        if (inCartList == null) {
            inCartList = mutableListOf()
        }
        if (inCartCount == null && guid != null) {
            inCartList.add(InCartModel(guid, lastValue))
        } else {
            inCartList.find { it.guid == guid }?.count = lastValue
        }
        saveLists(inCartList = moshi.adapterInCartList().toJson(inCartList))
    }

    private fun getInCartList(): List<InCartModel>? {
        val cachedData = sharedPreferences.getString(
            PreferencesConst.KEY_CACHED_IN_CART_LIST,
            null
        )
        return cachedData?.let { moshi.adapterInCartList().fromJson(it) }
    }

    private fun saveLists(
        inCartList: String? = null
    ) {
        if (inCartList != "") {
            editor.putString(PreferencesConst.KEY_CACHED_IN_CART_LIST, inCartList)
        }
        editor.apply()
    }
}
