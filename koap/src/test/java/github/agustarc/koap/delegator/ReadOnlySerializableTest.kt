package github.agustarc.koap.delegator

import github.agustarc.koap.BaseTest
import github.agustarc.koap.PreferenceHolder
import github.agustarc.koap.inferType
import org.junit.Assert.*
import org.junit.Test

/**
 * @author Leopold Baik
 * https://medium.com/@joongwon
 */
class ReadOnlySerializableTest : BaseTest() {

    @Test
    fun `should return default value if it exists`() {
        assertNotNull(Fixture.property)
    }

    @Test
    fun `should return null value if default value doesn't exist`() {
        assertNull(Fixture.nullProperty)
    }

    @Test
    fun `should be same returned object's properties`() {
        Fixture.property?.run {
            assertEquals("leopold", name)
            assertEquals(33, age)
        } ?: run { fail() }
    }

    private object Fixture : PreferenceHolder(name = "fixture") {
        val property: Account? by ReadOnlySerializable(type = inferType<Account>(), default = Account())
        val nullProperty: Account? by ReadOnlySerializable(type = inferType<Account>())
    }

    private data class Account(
        val name: String = "leopold",
        val age: Int = 33
    )
}