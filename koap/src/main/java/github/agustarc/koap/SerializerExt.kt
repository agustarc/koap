@file:Suppress("UNCHECKED_CAST")
package github.agustarc.koap

import java.lang.reflect.Type

/**
 * @author Leopold Baik
 * https://medium.com/@joongwon
 */

internal fun <T: Any> String.deserialize(type: Type): T? = getSerializerOrThrow().deserialize(this, type) as? T

internal fun <T> T.serialize() = getSerializerOrThrow().serialize(this)

private fun getSerializerOrThrow() = Koap.serializer ?: throw Error(Errors.SERIALIZER_NOT_SET)