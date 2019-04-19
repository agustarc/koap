package github.agustarc.koap.delegator

import github.agustarc.koap.BaseTest
import github.agustarc.koap.PreferenceHolder
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * @author Leopold Baik
 * https://medium.com/@joongwon
 */
class ReadWriteLongTest : BaseTest() {

    @Before
    fun setup() {
        Fixture.clearCache()
    }

    @Test
    fun `should verify value if a predicate exists`() {
        Fixture.value1 = 50L
        Assert.assertEquals(0L, Fixture.value1)
        Fixture.value1 = 101L
        Assert.assertEquals(101L, Fixture.value1)
    }

    @Test
    fun `should not update value if it doesn't pass a predicate`() {
        Fixture.value1 = 90L
        Assert.assertEquals(0L, Fixture.value1)
    }

    @Test
    fun `always update value if doesn't have any custom predicate`() {
        Fixture.value2 = 30L
        Assert.assertEquals(30L, Fixture.value2)
    }

    @Test
    fun `should return default value if it exists`() {
        Assert.assertEquals(10L, Fixture.value2)
    }

    @Test
    fun `default value must be zero`() {
        Assert.assertEquals(0L, Fixture.value3)
    }

    private object Fixture : PreferenceHolder(name = "fixture") {
        var value1: Long by ReadWriteLong(default = 0, predicate = { it > 100L })
        var value2: Long by ReadWriteLong(default = 10L)
        var value3: Long by ReadWriteLong()
    }
}