package github.agustarc.koap

import kotlin.reflect.KProperty

/**
 * @author Leopold Baik
 * https://medium.com/@joongwon
 */
interface CacheableProperty {
    fun setCacheValue(thisRef: PreferenceHolder, property: KProperty<*>)
    fun cacheable(): Boolean
}