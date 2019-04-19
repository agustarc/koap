package github.agustarc.koap.delegator

import github.agustarc.koap.BaseTest
import github.agustarc.koap.PreferenceHolder
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * @author Leopold Baik
 * https://medium.com/@joongwon
 */
class ReadOnlyIntTest : BaseTest() {

    @Test
    fun `should return default value if it exists`() {
        assertEquals(10, Fixture.count)
    }

    @Test
    fun `should return initial value if default value doesn't exist`() {
        assertEquals(0, Fixture.length)
    }

    private object Fixture : PreferenceHolder(name = "fixture") {
        val count: Int by ReadOnlyInt(default = 10)
        val length: Int by ReadOnlyInt()
    }

}