package github.agustarc.koap.delegator

import github.agustarc.koap.BaseTest
import github.agustarc.koap.PreferenceHolder
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * @author Leopold Baik
 * https://medium.com/@joongwon
 */
class ReadOnlyBooleanTest : BaseTest() {

    @Test
    fun `should return default value if it exists`() {
        assertTrue(Fixture.showLayoutBounds)
    }

    @Test
    fun `should return initial value if default value doesn't exist`() {
        assertFalse(Fixture.showSurfaceUpdates)
    }

    private object Fixture : PreferenceHolder(name = "fixture") {
        val showLayoutBounds: Boolean by ReadOnlyBoolean(default = true)
        val showSurfaceUpdates: Boolean by ReadOnlyBoolean()
    }
}