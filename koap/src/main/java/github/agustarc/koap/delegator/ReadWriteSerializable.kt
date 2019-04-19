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
class ReadWriteSerializable<T : Any>(
    private val type: Type,
    key: String = "",
    default: T? = null,
    caching: Boolean = true,
    predicate: (T?) -> Boolean = NULLABLE_UNSET_PREDICATE
) : ReadWritePreference<PreferenceHolder, T?>(key = key, default = default, caching = caching, predicate = predicate) {

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

    override fun setValue(thisRef: PreferenceHolder, property: KProperty<*>, value: T?) {
        if (existsNullablePredicate(predicate)) {
            if (predicate(value)) setSerializableValue(thisRef, property, value)
        } else {
            setSerializableValue(thisRef, property, value)
        }
    }

    private fun setSerializableValue(thisRef: PreferenceHolder, property: KProperty<*>, value: T?) {
        if (field == value) {
            return
        }

        if (Koap.isProduction()) thisRef.putString(key, property, value.serialize() ?: "")
        field = value
    }
}