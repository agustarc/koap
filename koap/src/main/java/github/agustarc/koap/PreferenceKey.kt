package github.agustarc.koap

import kotlin.reflect.KProperty

/**
 * @author Leopold Baik
 * https://medium.com/@joongwon
 */
internal fun getKey(key: String, property: KProperty<*>) = if (key.isNotEmpty()) key else "${property.name}Key"