package github.agustarc.koap.delegator

import github.agustarc.koap.*
import github.agustarc.koap.getFloat
import kotlin.reflect.KProperty

/**
 * @author Leopold Baik
 * https://medium.com/@joongwon
 */
class ReadWriteFloat(
    key: String = "",
    default: Float = 0.0F,
    caching: Boolean = true,
    predicate: (Float) -> Boolean = UNSET_PREDICATE
) : ReadWritePreference<PreferenceHolder, Float>(key = key, default = default, caching = caching, predicate = predicate) {

    override fun setCacheValue(thisRef: PreferenceHolder, property: KProperty<*>) {
        field = thisRef.getFloat(key, property, default)
    }
}