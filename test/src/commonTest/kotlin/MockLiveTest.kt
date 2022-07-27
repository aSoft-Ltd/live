import expect.expect
import live.MockLive
import kotlin.test.Test

class MockLiveTest {
    @Test
    fun should_be_able_to_track_live_objects() {
        val l = MockLive(45)
        expect(l).toHavePassedThrough(46, 57, 48)
    }
}