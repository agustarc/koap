package github.agustarc.koap

import kotlin.reflect.KProperty

/**
 * @author Leopold Baik
 * https://medium.com/@joongwon
 */
interface ClearableProperty {
    fun clear(thisRef: PreferenceHolder, property: KProperty<*>)
    fun clearCache()
}