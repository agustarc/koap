package github.agustarc.koap.delegator

import github.agustarc.koap.PreferenceHolder
import github.agustarc.koap.getBoolean
import kotlin.reflect.KProperty

/**
 * @author Leopold Baik
 * https://medium.com/@joongwon
 */
class ReadWriteBoolean(
    key: String = "",
    default: Boolean = false,
    caching: Boolean = true
) : ReadWritePreference<PreferenceHolder, Boolean>(key = key, default = default, caching = caching) {

    override fun setCacheValue(thisRef: PreferenceHolder, property: KProperty<*>) {
        field = thisRef.getBoolean(key, property, default)
    }
}