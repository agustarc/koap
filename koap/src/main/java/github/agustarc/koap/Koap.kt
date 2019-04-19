package github.agustarc.koap

import android.content.Context

/**
 * @author Leopold Baik
 * https://medium.com/@joongwon
 */
object Koap {
    var isTestMode: Boolean = false

    /**
     *  This cacheLoaded should be used to set serializer to use types that are not supported by SharedPreference.
     */
    var serializer: Serializer? = null

    fun bind(context: Context, vararg holders: PreferenceHolder) {
        holders.forEach { it.bind(context) }
    }

    internal fun isProduction() = !isTestMode
}