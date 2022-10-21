import expect.expect
import live.mutableLiveMapOf
import live.mutableLiveOf
import kotlin.test.Test

class LiveMapTest {
    val W1 = 0
    val W2 = 1

    @Test
    fun should_have_a_valid_syntax() {
        val live = mutableLiveMapOf<Int, String>()
        val values = mutableMapOf<Int, String>()
        val watcher = live.watch {
            println("Changed to $it")
            values.putAll(it)
        }
        live[0] = "Zero"
        expect(values[0]).toBe("Zero")

        live[1] = "One"
        expect(values[0]).toBe("Zero")
        expect(values[1]).toBe("One")

        live.clear()
        expect(live.value).toBeEmpty()
        watcher.stop()
        live.stopAll()
    }
}