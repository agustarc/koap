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
class ReadWriteIntTest : BaseTest() {

    @Before
    fun setup() {
        Fixture.clearCache()
    }

    @Test
    fun `should verify value if a predicate exists`() {
        Fixture.value1 = 50
        Assert.assertEquals(0, Fixture.value1)
        Fixture.value1 = 101
        Assert.assertEquals(101, Fixture.value1)
    }

    @Test
    fun `should not update value if it doesn't pass a predicate`() {
        Fixture.value1 = 90
        Assert.assertEquals(0, Fixture.value1)
    }

    @Test
    fun `always update value if doesn't have any custom predicate`() {
        Fixture.value2 = 30
        Assert.assertEquals(30, Fixture.value2)
    }

    @Test
    fun `should return default value if it exists`() {
        Assert.assertEquals(10, Fixture.value2)
    }

    @Test
    fun `default value must be zero`() {
        Assert.assertEquals(0, Fixture.value3)
    }

    private object Fixture : PreferenceHolder(name = "fixture") {
        var value1: Int by ReadWriteInt(default = 0, predicate = { it > 100 })
        var value2: Int by ReadWriteInt(default = 10)
        var value3: Int by ReadWriteInt()
    }

}