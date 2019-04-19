package github.agustarc.koap

import java.lang.reflect.Type

/**
 * @author Leopold Baik
 * https://medium.com/@joongwon
 */
interface Serializer {
    fun serialize(toSerialize: Any?): String?

    fun deserialize(serialized: String?, type: Type): Any?
}
