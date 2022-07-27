import expect.expect
import expect.toBeEqualTo
import live.mutableLiveOf
import kotlin.test.Test

class LiveHistoryTest {
    @Test
    fun should_begin_with_an_empty_history() {
        val l = mutableLiveOf(5)
        expect(collection = l.history).toBeEqualTo(listOf())
    }

    @Test
    fun should_be_able_to_store_values_in_history() {
        val l = mutableLiveOf(5, 5)
        l.value = 6
        l.value = 7
        expect(collection = l.history).toBeEqualTo(listOf(5, 6))
    }

    @Test
    fun should_not_store_more_than_the_provided_capacity() {
        val l = mutableLiveOf(5, 2)
        l.value = 6
        l.value = 7
        l.value = 8
        expect(collection = l.history).toBeEqualTo(listOf(6, 7))
    }

    @Test
    fun should_be_able_to_undo() {
        val l = mutableLiveOf(5, 2)
        l.value = 8
        expect(l.value).toBe(8)
        l.undo()
        expect(l.value).toBe(5)
    }

    @Test
    fun should_track_history_properly_after_a_single_undo() {
        val l = mutableLiveOf(5, 5)
        l.value = 6
        l.value = 7
        l.undo()
        expect(l.value).toBe(6)
        expect(l.history).toBe(listOf(5, 6, 7))
    }

    @Test
    fun should_track_history_properly_after_a_two_undos() {
        val l = mutableLiveOf(5, 5)
        l.value = 6
        l.value = 7
        l.value = 8
        l.undo()
        expect(l.value).toBe(7)
        l.undo()
        expect(l.value).toBe(6)
        expect(l.history).toBe(listOf(5, 6, 7, 8))
    }

    @Test
    fun should_not_be_able_to_undo_beyond_history_capacity() {
        val l = mutableLiveOf(5, 2)
        l.value = 6
        l.value = 7
        l.value = 8
        l.undo()
        expect(l.value).toBe(7)
        expect(l.history).toBe(listOf(7, 8))
        l.undo()
        expect(l.value).toBe(7)
        expect(l.history).toBe(listOf(7, 8))
        l.undo()
        expect(l.value).toBe(7)
        expect(l.history).toBe(listOf(7, 8))
        l.undo()
        expect(l.value).toBe(7)
        expect(l.history).toBe(listOf(7, 8))
    }

    @Test
    fun should_not_be_able_to_undo_beyond_history_size() {
        val l = mutableLiveOf(5, 10)
        l.value = 6
        l.value = 7
        l.undo()
        l.undo()
        l.undo()
        l.undo()
        expect(l.value).toBe(5)
        expect(l.history).toBe(listOf(5, 6, 7))
    }

    @Test
    fun should_be_able_to_redo() {
        val l = mutableLiveOf(5, 5)
        l.value = 6
        l.value = 7
        l.value = 8
        l.undo()
        expect(l.value).toBe(7)
        l.undo()
        expect(l.value).toBe(6)
        l.redo()
        expect(l.value).toBe(7)
        l.redo()
        expect(l.value).toBe(8)
        expect(l.history).toBe(listOf(5, 6, 7, 8))
    }

    @Test
    fun should_not_be_able_to_redo_past_undo_size() {
        val l = mutableLiveOf(5, 5)
        l.value = 6
        l.value = 7
        l.value = 8
        l.undo()
        expect(l.value).toBe(7)
        l.redo()
        expect(l.value).toBe(8)
        l.redo()
        expect(l.value).toBe(8)
        l.redo()
        expect(l.value).toBe(8)
        expect(l.history).toBe(listOf(5, 6, 7, 8))
    }
}