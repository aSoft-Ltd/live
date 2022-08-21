import live.*
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

class LiveExpectationTest {

    @Test
    fun should_be_at_a_specific_state() {
        val l = mutableLiveOf(5)
        expect(l).toBeIn(5)
    }

    @Test
    fun should_be_able_to_track_live_objects() {
        val l = mutableLiveOf(10)
        l.value = 11
        l.value = 12
        l.value = 13
        val (i1) = expect(l).toHaveGoneThrough(11, 12, 13)
        assertEquals(11, i1)
    }

    @Test
    fun should_be_able_to_track_classes_of_objects() {
        val l = mutableLiveOf(10)
        l.value = 11
        l.value = 12
        l.value = 13
        val (i1, _, i3) = expect(l).toHaveGoneThrough(Int::class, Int::class, Int::class)
        assertEquals(11, i1)
        assertEquals(13, i3)
    }

    @Test
    fun should_be_able_to_track_classes_of_objects_through_generics() {
        val l = mutableLiveOf(10)
        l.value = 11
        l.value = 12
        l.value = 13
        val (s1) = expect(l).toHaveGoneThrough3<Int, Int, Int>()
        assertEquals(11, s1)
    }

    @Test
    @Ignore // TODO: Try to make it work with nulls
    fun should_work_with_nullables() {
        val l: MutableLive<Int?> = mutableLiveOf(10)
        l.value = 11
        l.value = null
        l.value = 13
        val (s1, s2, s3, _) = expect(l).toHaveGoneThrough3<Int, Int?, Int>()
        assertEquals(11, s1)
        assertEquals(null, s2)
        assertEquals(13, s3)
    }
}