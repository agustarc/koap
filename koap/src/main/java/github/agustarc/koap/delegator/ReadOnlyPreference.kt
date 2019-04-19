package github.agustarc.koap.delegator

import github.agustarc.koap.*
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * @author Leopold Baik
 * https://medium.com/@joongwon
 */
abstract class ReadOnlyPreference<in R : PreferenceHolder, T>(
    internal val key: String = "",
    internal val default: T,
    internal val caching: Boolean = false
) : ReadOnlyProperty<R, T>,
    ClearableProperty,
    CacheableProperty {

    protected var field: T = default
    protected var cacheLoaded = false
    protected fun cacheUsable(): Boolean = caching && cacheLoaded

    override fun cacheable(): Boolean = caching

    override fun clear(thisRef: PreferenceHolder, property: KProperty<*>) {}

    override fun clearCache() {
        field = default
    }

    override operator fun getValue(thisRef: R, property: KProperty<*>): T {
        return if (Koap.isTestMode || cacheUsable()) field else {
            cacheLoaded = true
            field = thisRef.getPreferencePrimitiveValue(key, property, default)
            field
        }
    }
}