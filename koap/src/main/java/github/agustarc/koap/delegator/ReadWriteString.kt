package github.agustarc.koap.delegator

import github.agustarc.koap.*
import github.agustarc.koap.getString
import kotlin.reflect.KProperty

/**
 * @author Leopold Baik
 * https://medium.com/@joongwon
 */
class ReadWriteString(
    key: String = "",
    default: String = "",
    caching: Boolean = true,
    predicate: (String) -> Boolean = UNSET_PREDICATE
) : ReadWritePreference<PreferenceHolder, String>(key = key, default = default, caching = caching, predicate = predicate) {

    override fun setCacheValue(thisRef: PreferenceHolder, property: KProperty<*>) {
        field = thisRef.getString(key, property, default)
    }
}