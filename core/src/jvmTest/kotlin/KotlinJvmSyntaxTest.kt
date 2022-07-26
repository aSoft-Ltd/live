import expect.expect
import koncurrent.MockExecutor
import live.*
import kotlin.test.Test

class KotlinJvmSyntaxTest {

    val executor = MockExecutor()

    @Test
    fun should_have_a_valid_syntax() {
        val liveInt = mutableLiveOf(1)
        val readableLive: Live<Int> = liveInt
        var counter1 = 0
        val watcher1 = readableLive.watch(mode = WatchMode.Eagerly, executor) {
            counter1++
        }
        expect(counter1).toBe(1)
        liveInt.value = 2
        liveInt.value = 3
        var counter2 = 0
        expect(counter1).toBe(3)
        val watcher2 = liveInt.watch(WatchMode.Eagerly, executor) {
            counter2++
        }
        expect(counter2).toBe(1)
        liveInt.value = 4
        liveInt.value = 5
        expect(counter1).toBe(5)
        expect(counter2).toBe(3)
        watcher1.stop()
        liveInt.value = 6
        liveInt.value = 7
        expect(counter1).toBe(5)
        expect(counter2).toBe(5)
        watcher2.stop()
        liveInt.value = 8
        expect(counter1).toBe(5)
        expect(counter2).toBe(5)
        watcher2.stop()
        liveInt.value = 9
        liveInt.value = 10
        expect(counter1).toBe(5)
        expect(counter2).toBe(5)
    }
}