package github.agustarc.koap.delegator

import github.agustarc.koap.*
import github.agustarc.koap.deserialize
import github.agustarc.koap.getString
import java.lang.reflect.Type
import kotlin.reflect.KProperty

/**
 * @author Leopold Baik
 * https://medium.com/@joongwon
 */
class ReadOnlySerializable<T : Any>(
    private val type: Type,
    key: String = "",
    default: T? = null,
    caching: Boolean = true
) : ReadOnlyPreference<PreferenceHolder, T?>(key = key, default = default, caching = caching) {

    override fun setCacheValue(thisRef: PreferenceHolder, property: KProperty<*>) {
        if (thisRef.hasKey(key, property)) {
            field = thisRef.getString(key, property, "").deserialize(type)
        }
    }

    override fun getValue(thisRef: PreferenceHolder, property: KProperty<*>): T? {
        return if (Koap.isTestMode || cacheUsable()) field else {
            if (thisRef.hasKey(key, property)) {
                field = thisRef.getString(key, property, "").deserialize(type)
                cacheLoaded = true
                field
            } else default
        }
    }
}