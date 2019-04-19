package github.agustarc.koap.delegator

import github.agustarc.koap.PreferenceHolder
import github.agustarc.koap.getInt
import kotlin.reflect.KProperty

/**
 * @author Leopold Baik
 * https://medium.com/@joongwon
 */
class ReadOnlyInt(
    key: String = "",
    default: Int = 0,
    caching: Boolean = true
) : ReadOnlyPreference<PreferenceHolder, Int>(key = key, default = default, caching = caching) {

    override fun setCacheValue(thisRef: PreferenceHolder, property: KProperty<*>) {
        field = thisRef.getInt(key, property, default)
    }
}