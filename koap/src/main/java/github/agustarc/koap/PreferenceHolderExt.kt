package github.agustarc.koap

import kotlin.reflect.KProperty

/**
 * @author Leopold Baik
 * https://medium.com/@joongwon
 */

internal fun PreferenceHolder.getString(key: String, property: KProperty<*>, default: String): String {
    return getPreferenceOrThrow().getString(getKey(key, property), default) as String
}

internal fun PreferenceHolder.putString(key: String, property: KProperty<*>, value: String) {
    getEditorOrThrow().putString(getKey(key, property), value).apply()
}

internal fun PreferenceHolder.getInt(key: String, property: KProperty<*>, default: Int): Int {
    return getPreferenceOrThrow().getInt(getKey(key, property), default)
}

internal fun PreferenceHolder.putInt(key: String, property: KProperty<*>, value: Int) {
    getEditorOrThrow().putInt(getKey(key, property), value).apply()
}

internal fun PreferenceHolder.getLong(key: String, property: KProperty<*>, default: Long): Long {
    return getPreferenceOrThrow().getLong(getKey(key, property), default)
}

internal fun PreferenceHolder.putLong(key: String, property: KProperty<*>, value: Long) {
    getEditorOrThrow().putLong(getKey(key, property), value).apply()
}

internal fun PreferenceHolder.getFloat(key: String, property: KProperty<*>, default: Float): Float {
    return getPreferenceOrThrow().getFloat(getKey(key, property), default)
}

internal fun PreferenceHolder.putFloat(key: String, property: KProperty<*>, value: Float) {
    getEditorOrThrow().putFloat(getKey(key, property), value).apply()
}

internal fun PreferenceHolder.getBoolean(key: String, property: KProperty<*>, default: Boolean): Boolean {
    return getPreferenceOrThrow().getBoolean(getKey(key, property), default)
}

internal fun PreferenceHolder.putBoolean(key: String, property: KProperty<*>, value: Boolean) {
    getEditorOrThrow().putBoolean(getKey(key, property), value).apply()
}

internal fun PreferenceHolder.hasKey(key: String, property: KProperty<*>): Boolean =
    getPreferenceOrThrow().contains(getKey(key, property))


@Suppress("UNCHECKED_CAST")
internal fun <T> PreferenceHolder.getPreferencePrimitiveValue(key: String, property: KProperty<*>, default: T): T =
    when (default) {
        is String -> getString(key, property, default as String) as T
        is Int -> getInt(key, property, default as Int) as T
        is Long -> getLong(key, property, default as Long) as T
        is Float -> getFloat(key, property, default as Float) as T
        is Boolean -> getBoolean(key, property, default as Boolean) as T
        else -> default
    }

internal fun <T> PreferenceHolder.putPreferencePrimitiveValue(key: String, property: KProperty<*>, value: T) {
    when (value) {
        is String -> putString(key, property, value)
        is Int -> putInt(key, property, value)
        is Long -> putLong(key, property, value)
        is Float -> putFloat(key, property, value)
        is Boolean -> putBoolean(key, property, value)
    }
}