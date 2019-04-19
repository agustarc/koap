package github.agustarc.koap.delegator

import github.agustarc.koap.BaseTest
import github.agustarc.koap.PreferenceHolder
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * @author Leopold Baik
 * https://medium.com/@joongwon
 */
class ReadWriteBooleanTest : BaseTest() {

    @Before
    fun setup() {
        Fixture.clearCache()
    }

    @Test
    fun `should return updated value`() {
        assertTrue(Fixture.showLayoutBounds)
        Fixture.showLayoutBounds = false
        assertFalse(Fixture.showLayoutBounds)
    }

    @Test
    fun `default value must be false` () {
        assertFalse(Fixture.showSurfaceUpdates)
    }

    private object Fixture : PreferenceHolder(name = "fixture") {
        var showLayoutBounds: Boolean by ReadWriteBoolean(default = true)
        var showSurfaceUpdates: Boolean by ReadWriteBoolean()
    }
}