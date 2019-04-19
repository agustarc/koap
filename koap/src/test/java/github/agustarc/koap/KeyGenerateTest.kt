package github.agustarc.koap

import org.junit.Assert.*
import org.junit.Test
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties

/**
 * @author Leopold Baik
 * https://medium.com/@joongwon
 */
class KeyGenerateTest : BaseTest() {

    @Test
    fun `generated key should not be null`() {
        properties().forEach {
            assertNotNull(getKey("", it))
        }
    }

    @Test
    fun `generated key should have a suffix if key parameter is empty`() {
        properties().forEach {
            assertTrue(getKey("", it).endsWith("Key"))
        }
    }

    @Test
    fun `generated key and key parameter are should exactly same`() {
        properties().forEach {
            val key = it.name.plus("custom")
            val generatedKey = getKey(key, it)
            assertEquals(key, generatedKey)
        }
    }

    private fun properties(): Collection<KProperty1<out User, Any?>> =
        User("leopold", "baik")::class.memberProperties

    data class User(
        val id: String,
        val name: String
    )
}