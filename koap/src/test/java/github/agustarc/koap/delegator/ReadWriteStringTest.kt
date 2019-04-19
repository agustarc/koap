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
class ReadWriteStringTest : BaseTest() {

    @Before
    fun setup() {
        Fixture.clearCache()
    }

    @Test
    fun `should verify value if a predicate exists`() {
        Fixture.value1 = "Leo"
        Assert.assertEquals("", Fixture.value1)
        Fixture.value1 = "Leopold Baik"
        Assert.assertEquals("Leopold Baik", Fixture.value1)
    }

    @Test
    fun `should not update value if it doesn't pass a predicate`() {
        Fixture.value1 = "hi"
        Assert.assertEquals("", Fixture.value1)
    }

    @Test
    fun `always update value if doesn't have any custom predicate`() {
        Fixture.value2 = "Hi"
        Assert.assertEquals("Hi", Fixture.value2)
    }

    @Test
    fun `should return default value if it exists`() {
        Assert.assertEquals("Leopold", Fixture.value2)
    }

    @Test
    fun `default value must be empty string`() {
        Assert.assertEquals("", Fixture.value3)
    }

    @Test
    fun `should startWith a 'test'`() {
        Fixture.value4 = "ABC"
        Assert.assertEquals("", Fixture.value4)
        Fixture.value4 = "testABC"
        Assert.assertEquals("testABC", Fixture.value4)
    }

    private object Fixture : PreferenceHolder(name = "fixture") {
        var value1: String by ReadWriteString(default = "", predicate = { it.length > 3 })
        var value2: String by ReadWriteString(default = "Leopold")
        var value3: String by ReadWriteString()
        var value4: String by ReadWriteString(predicate = { it.startsWith("test") })
    }
}