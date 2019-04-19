package github.agustarc.koap

import com.google.gson.Gson
import java.lang.reflect.Type

/**
 * @author Leopold Baik
 * https://medium.com/@joongwon
 */
open class BaseSerializableTest : BaseTest() {
    init {
        Koap.serializer = GsonSerializer(Gson())
    }

    protected class GsonSerializer(private val gson: Gson) : Serializer {
        override fun serialize(toSerialize: Any?): String? = gson.toJson(toSerialize)
        override fun deserialize(serialized: String?, type: Type): Any? = try {
            gson.fromJson<Any>(serialized, type)
        } catch (e: Throwable) {
            throw Error("Error in parsing to $type. The string to parse: \"$this\"", e)
        }
    }
}