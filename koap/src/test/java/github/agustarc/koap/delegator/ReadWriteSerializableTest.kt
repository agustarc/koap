package github.agustarc.koap.delegator

import github.agustarc.koap.PreferenceHolder
import github.agustarc.koap.BaseSerializableTest
import github.agustarc.koap.inferType
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * @author Leopold Baik
 * https://medium.com/@joongwon
 */
class ReadWriteSerializableTest : BaseSerializableTest() {

    @Before
    fun setup() {
        Fixture.clearCache()
    }

    @Test
    fun `should return not null if default value exists`() {
        assertNotNull(Fixture.value1)
    }

    @Test
    fun `should return null if it doesn't have default value`() {
        assertNull(Fixture.value2)
    }

    @Test
    fun `always update value if doesn't have any custom predicate`() {
        Fixture.value1 = User(id = "100", name = "Leopold", age = 33)
        assertEquals("100", Fixture.value1?.id)
        assertEquals("Leopold", Fixture.value1?.name)
        assertEquals(33, Fixture.value1?.age)
    }

    @Test
    fun `should not update value if it doesn't pass predicate`() {
        Fixture.value3 = User(id = "200", name = "Bob", age = 27)
        assertEquals("101", Fixture.value3?.id)
        assertEquals("Leopold", Fixture.value3?.name)
        assertEquals(33, Fixture.value3?.age)
    }

    @Test
    fun `should update value if it passed predicate`() {
        Fixture.value3 = User(id = "200", name = "Bob", age = 37)
        assertEquals("200", Fixture.value3?.id)
        assertEquals("Bob", Fixture.value3?.name)
        assertEquals(37, Fixture.value3?.age)
    }

    private object Fixture : PreferenceHolder(name = "fixture") {
        var value1: User? by ReadWriteSerializable(type = inferType<User>(), default = User(id = "", name = "", age = 0))
        var value2: User? by ReadWriteSerializable(type = inferType<User>())
        var value3: User? by ReadWriteSerializable(type = inferType<User>(), default = User(id = "101", name = "Leopold", age = 33), predicate = { it?.age ?: 0 > 35 })
    }

    private data class User(
        val id: String,
        val name: String,
        val age: Int
    )
}