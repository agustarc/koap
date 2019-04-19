package github.agustarc.koap

import github.agustarc.koap.delegator.*
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * @author Leopold Baik
 * https://medium.com/@joongwon
 */
class PreferenceHolderTest : BaseTest() {

    @Test
    fun `should be initial value after call clear()`() {
        Fixture.value1 = "Leopold"
        Fixture.value2 = 100
        Fixture.value3 = 10.0F
        Fixture.value4 = 100L
        Fixture.value5 = true
        Fixture.clear()

        assertEquals("", Fixture.value1)
        assertEquals(0, Fixture.value2)
        assertEquals(0.0F, Fixture.value3)
        assertEquals(0L, Fixture.value4)
        assertEquals(false, Fixture.value5)
    }

    @Test
    fun `should be default value after call clear() if it has a default value`() {
        Fixture.value6 = "Leopold"
        Fixture.value7 = 100
        Fixture.value8 = 10.0F
        Fixture.value9 = 100L
        Fixture.value10 = false
        Fixture.clear()

        assertEquals("Leopold", Fixture.value6)
        assertEquals(50, Fixture.value7)
        assertEquals(30.0F, Fixture.value8)
        assertEquals(30L, Fixture.value9)
        assertEquals(true, Fixture.value10)
    }

    private object Fixture : PreferenceHolder(name = "fixture") {
        var value1: String by ReadWriteString()
        var value2: Int by ReadWriteInt()
        var value3: Float by ReadWriteFloat()
        var value4: Long by ReadWriteLong()
        var value5: Boolean by ReadWriteBoolean()

        var value6: String by ReadWriteString(default = "Leopold")
        var value7: Int by ReadWriteInt(default = 50)
        var value8: Float by ReadWriteFloat(default = 30.0F)
        var value9: Long by ReadWriteLong(default = 30L)
        var value10: Boolean by ReadWriteBoolean(default = true)
    }
}