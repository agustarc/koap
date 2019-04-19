package github.agustarc.koap.delegator

import github.agustarc.koap.*
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * @author Leopold Baik
 * https://medium.com/@joongwon
 */
abstract class ReadWritePreference<in R : PreferenceHolder, T>(
    internal val key: String = "",
    internal val default: T,
    internal val caching: Boolean = false,
    internal val predicate: (T) -> Boolean = { true }
) : ReadWriteProperty<R, T>,
    ClearableProperty,
    CacheableProperty {

    protected var field: T = default
    protected var cacheLoaded = false
    protected fun cacheUsable(): Boolean = caching && cacheLoaded

    override fun cacheable(): Boolean = caching

    override fun clear(thisRef: PreferenceHolder, property: KProperty<*>) {
        clearCache()

        if (Koap.isProduction()) thisRef.putPreferencePrimitiveValue(key, property, default)
    }

    override fun clearCache() {
        field = default
    }

    override operator fun getValue(thisRef: R, property: KProperty<*>): T {
        return if (Koap.isTestMode || cacheUsable()) field else {
            field = thisRef.getPreferencePrimitiveValue(key, property, default)
            cacheLoaded = true
            field
        }
    }

    override operator fun setValue(thisRef: R, property: KProperty<*>, value: T) {
        if (existsPredicate(predicate)) {
            if (predicate(value)) setValueToPreference(thisRef, property, value)
        } else {
            setValueToPreference(thisRef, property, value)
        }
    }

    private fun setValueToPreference(thisRef: PreferenceHolder, property: KProperty<*>, value: T) {
        if (field == value) {
            return
        }

        if (Koap.isProduction()) thisRef.putPreferencePrimitiveValue(key, property, value)
        field = value
    }
}