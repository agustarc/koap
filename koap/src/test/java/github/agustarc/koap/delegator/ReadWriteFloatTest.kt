package github.agustarc.koap.delegator

import github.agustarc.koap.BaseTest
import github.agustarc.koap.PreferenceHolder
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * @author Leopold Baik
 * https://medium.com/@joongwon
 */
class ReadWriteFloatTest : BaseTest() {

    @Before
    fun setup() {
        Fixture.clearCache()
    }

    @Test
    fun `should verify value if a predicate exists`() {
        Fixture.value1 = 0.5F
        assertEquals(0.0F, Fixture.value1)
        Fixture.value1 = 1.1F
        assertEquals(1.1F, Fixture.value1)
    }

    @Test
    fun `should not update value if it doesn't pass a predicate`() {
        Fixture.value1 = 0.8F
        assertEquals(0.0F, Fixture.value1)
    }

    @Test
    fun `always update value if doesn't have any custom predicate`() {
        Fixture.value2 = 0.5F
        assertEquals(0.5F, Fixture.value2)
    }

    @Test
    fun `should return default value if it exists`() {
        assertEquals(10.0F, Fixture.value2)
    }

    @Test
    fun `default value must be zero`() {
        assertEquals(0.0F, Fixture.value3)
    }

    private object Fixture : PreferenceHolder(name = "fixture") {
        var value1: Float by ReadWriteFloat(default = 0.0f, predicate = { it > 1.0F })
        var value2: Float by ReadWriteFloat(default = 10.0F)
        var value3: Float by ReadWriteFloat()
    }
}