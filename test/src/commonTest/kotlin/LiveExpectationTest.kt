import live.expect
import live.mutableLiveOf
import kotlin.test.Test

class LiveExpectationTest {
    @Test
    fun should_be_able_to_track_live_objects() {
        val l = mutableLiveOf(10)
        l.value = 11
        l.value = 12
        l.value = 13
        expect(l).toHaveGoneThrough(11, 12, 13)
    }
}