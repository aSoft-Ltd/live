import expect.expect
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import live.Live
import test.asyncTest
import kotlin.test.Test

class ConcurrencyTest {
    @Test
    fun should_be_able_to_switch_threads() = asyncTest {
        val live = Live(1)
        val watcher1 = live.watch {
            launch(Dispatchers.Default) {
                println("Watcher 1: Current Value: $it")
            }
        }

        val watcher2 = live.watch {
            launch(Dispatchers.Unconfined) {
                println("Watcher 2: Current Value: $it")
            }
        }
        withContext(Dispatchers.Default) {
            live.value = 2
        }
        withContext(Dispatchers.Unconfined) {
            live.value = 3
        }
        watcher1.stop()
        watcher2.stop()
        expect(live.value).toBe(3)
    }
}