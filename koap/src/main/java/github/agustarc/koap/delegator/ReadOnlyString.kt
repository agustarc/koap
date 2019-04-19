package github.agustarc.koap.delegator

import github.agustarc.koap.PreferenceHolder
import github.agustarc.koap.getString
import kotlin.reflect.KProperty

/**
 * @author Leopold Baik
 * https://medium.com/@joongwon
 */
class ReadOnlyString(
    key: String = "",
    default: String = "",
    caching: Boolean = true
) : ReadOnlyPreference<PreferenceHolder, String>(key = key, default = default, caching = caching) {

    override fun setCacheValue(thisRef: PreferenceHolder, property: KProperty<*>) {
        field = thisRef.getString(key, property, default)
    }
}