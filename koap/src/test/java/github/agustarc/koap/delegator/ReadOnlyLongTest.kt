package github.agustarc.koap.delegator

import github.agustarc.koap.BaseTest
import github.agustarc.koap.PreferenceHolder
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * @author Leopold Baik
 * https://medium.com/@joongwon
 */
class ReadOnlyLongTest : BaseTest() {

    @Test
    fun `should return default value if it exists`() {
        assertEquals(50L, Fixture.millis)
    }

    @Test
    fun `should return initial value if default value doesn't exist`() {
        assertEquals(0L, Fixture.duration)
    }

    private object Fixture : PreferenceHolder(name = "fixture") {
        val millis: Long by ReadOnlyLong(default = 50L)
        val duration: Long by ReadOnlyLong()
    }

}