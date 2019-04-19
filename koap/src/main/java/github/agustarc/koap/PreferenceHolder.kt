package github.agustarc.koap

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty1
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.isAccessible

/**
 * @author Leopold Baik
 * https://medium.com/@joongwon
 */
abstract class PreferenceHolder(
    private val name: String,
    private val default: Boolean = false,
    private val mode: Int = Context.MODE_PRIVATE,
    private val cacheStrategy: CacheStrategy = CacheStrategy.LAZY
) {
    private var preferences: SharedPreferences? = null

    internal fun bind(context: Context) {
        if (preferences == null) {
            preferences = getSharedPreferences(context, name, default, mode)

            if (cacheStrategy == CacheStrategy.EAGER) {
                forEachDelegate<CacheableProperty> { delegate, property ->
                    if (delegate.cacheable()) delegate.setCacheValue(this, property)
                }
            }
        }
    }

    fun clear() {
        forEachDelegate<ClearableProperty> { delegate, property -> delegate.clear(this, property) }
    }

    fun clearCache() {
        forEachDelegate<ClearableProperty> { delegate, _ -> delegate.clearCache() }
    }

    private inline fun <reified T : Any> forEachDelegate(block: (T, KProperty<*>) -> Unit) {
        val properties = this::class.declaredMemberProperties.filterIsInstance<KProperty1<PreferenceHolder, *>>()
        for (prop in properties) {
            val prevAccessible = prop.isAccessible
            if (!prevAccessible) prop.isAccessible = true

            val delegate = prop.getDelegate(this)
            if (delegate is T) block(delegate, prop)
            prop.isAccessible = prevAccessible
        }
    }

    internal fun getPreferenceOrThrow(): SharedPreferences = preferences ?: throw Error(Errors.CONTEXT_NOT_SET)
    internal fun getEditorOrThrow(): SharedPreferences.Editor = getPreferenceOrThrow().edit()

    private fun getSharedPreferences(context: Context, name: String, default: Boolean, mode: Int): SharedPreferences =
        if (default) PreferenceManager.getDefaultSharedPreferences(context.applicationContext)
        else context.applicationContext.getSharedPreferences(name, mode)
}