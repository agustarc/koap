package github.agustarc.koap.delegator

import github.agustarc.koap.PreferenceHolder
import github.agustarc.koap.getLong
import kotlin.reflect.KProperty

/**
 * @author Leopold Baik
 * https://medium.com/@joongwon
 */
class ReadOnlyLong(
    key: String = "",
    default: Long = 0L,
    caching: Boolean = true
) : ReadOnlyPreference<PreferenceHolder, Long>(key = key, default = default, caching = caching) {

    override fun setCacheValue(thisRef: PreferenceHolder, property: KProperty<*>) {
        field = thisRef.getLong(key, property, default)
    }
}