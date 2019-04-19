package github.agustarc.koap.delegator

import github.agustarc.koap.BaseTest
import github.agustarc.koap.PreferenceHolder
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * @author Leopold Baik
 * https://medium.com/@joongwon
 */
class ReadOnlyStringTest : BaseTest() {

    @Test
    fun `should return default value if it exists`() {
        assertEquals("leopold", Fixture.id)
    }

    @Test
    fun `should return empty value if default value doesn't exist`() {
        assertEquals("", Fixture.name)
    }

    private object Fixture : PreferenceHolder(name = "fixture") {
        val id: String by ReadOnlyString(default = "leopold")
        val name: String by ReadOnlyString()
    }
}