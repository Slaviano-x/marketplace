package ru.ozon.route256.coredataimpl.resourceprovider

import android.content.Context
import ru.ozon.route256.coredataapi.ResourceProvider
import javax.inject.Inject

class ResourceProviderImpl @Inject constructor(
    private val context: Context,
) : ResourceProvider {
    override fun getString(resId: Int): String = context.getString(resId)

    override fun getString(resId: Int, vararg formatArgs: Any): String =
        context.resources.getString(resId, *formatArgs)

    override fun getStringArray(resId: Int): Array<out String> =
        context.resources.getStringArray(resId)

    override fun getQuantityString(resId: Int, quantity: Int, vararg formatArgs: Any): String =
        context.resources.getQuantityString(resId, quantity, *formatArgs)

    override fun getIntArray(resId: Int): IntArray =
        context.resources.getIntArray(resId)
}
