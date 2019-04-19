package github.agustarc.koap.delegator

import github.agustarc.koap.BaseTest
import github.agustarc.koap.PreferenceHolder
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * @author Leopold Baik
 * https://medium.com/@joongwon
 */
class ReadOnlyFloatTest : BaseTest() {

    @Test
    fun `should return default value if it exists`() {
        assertEquals(4.5f, Fixture.rate)
    }

    @Test
    fun `should return initial value if default value doesn't exist`() {
        assertEquals(0.0f, Fixture.average)
    }

    private object Fixture : PreferenceHolder(name = "fixture") {
        val rate: Float by ReadOnlyFloat(default = 4.5f)
        val average: Float by ReadOnlyFloat()
    }
}