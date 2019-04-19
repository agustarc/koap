package github.agustarc.koap

/**
 * @author Leopold Baik
 * https://medium.com/@joongwon
 */

inline fun <reified T> inferType() = object : TypeToken<T>() {}.type