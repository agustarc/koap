package github.agustarc.koap.delegator

import github.agustarc.koap.*
import github.agustarc.koap.getInt
import kotlin.reflect.KProperty

/**
 * @author Leopold Baik
 * https://medium.com/@joongwon
 */
class ReadWriteInt(
    key: String = "",
    default: Int = 0,
    caching: Boolean = true,
    predicate: (Int) -> Boolean = UNSET_PREDICATE
) : ReadWritePreference<PreferenceHolder, Int>(key = key, default = default, caching = caching, predicate = predicate) {

    override fun setCacheValue(thisRef: PreferenceHolder, property: KProperty<*>) {
        field = thisRef.getInt(key, property, default)
    }
}