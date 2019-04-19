package github.agustarc.koap.delegator

import github.agustarc.koap.PreferenceHolder
import github.agustarc.koap.getFloat
import kotlin.reflect.KProperty

/**
 * @author Leopold Baik
 * https://medium.com/@joongwon
 */
class ReadOnlyFloat(
    key: String = "",
    default: Float = 0.0F,
    caching: Boolean = true
) : ReadOnlyPreference<PreferenceHolder, Float>(key = key, default = default, caching = caching) {

    override fun setCacheValue(thisRef: PreferenceHolder, property: KProperty<*>) {
        field = thisRef.getFloat(key, property, default)
    }
}