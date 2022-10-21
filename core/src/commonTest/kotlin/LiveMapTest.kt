import expect.expect
import live.mutableLiveOf
import kotlin.test.Test

class LiveMapTest {

    val W1 = 0
    val W2 = 1

    @Test
    fun should_have_a_valid_syntax() {
        val liveInt = mutableLiveOf(1)
        val liveString = liveInt.map { "String: $it" }
        val values = mutableMapOf<Int, String>()
        val watcher1 = liveString.watch {
            values[W1] = it
        }
        liveInt.value = 2
        expect(values[W1]).toBe("String: 2")
        liveInt.value = 3
        expect(values[W1]).toBe("String: 3")
        val watcher2 = liveString.watch {
            values[W2] = it
        }
        liveInt.value = 4
        expect(values[W1]).toBe("String: 4")
        expect(values[W2]).toBe("String: 4")
        liveInt.value = 5
        expect(values[W1]).toBe("String: 5")
        expect(values[W2]).toBe("String: 5")
        watcher1.stop()
        liveInt.value = 6
        expect(values[W1]).toBe("String: 5")
        expect(values[W2]).toBe("String: 6")
        liveInt.value = 7
        expect(values[W1]).toBe("String: 5")
        expect(values[W2]).toBe("String: 7")
        watcher2.stop()
        liveInt.value = 8
        expect(values[W1]).toBe("String: 5")
        expect(values[W2]).toBe("String: 7")
        liveInt.value = 9
        expect(values[W1]).toBe("String: 5")
        expect(values[W2]).toBe("String: 7")
        liveInt.value = 10
        expect(values[W1]).toBe("String: 5")
        expect(values[W2]).toBe("String: 7")
    }
}