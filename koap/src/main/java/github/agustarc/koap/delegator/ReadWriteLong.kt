package github.agustarc.koap.delegator

import github.agustarc.koap.*
import github.agustarc.koap.getLong
import kotlin.reflect.KProperty

/**
 * @author Leopold Baik
 * https://medium.com/@joongwon
 */
class ReadWriteLong(
    key: String = "",
    default: Long = 0L,
    caching: Boolean = true,
    predicate: (Long) -> Boolean = UNSET_PREDICATE
) : ReadWritePreference<PreferenceHolder, Long>(key = key, default = default, caching = caching, predicate = predicate) {

    override fun setCacheValue(thisRef: PreferenceHolder, property: KProperty<*>) {
        field = thisRef.getLong(key, property, default)
    }
}