import expect.expect
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import live.WatchMode
import live.mutableLiveOf
import live.watch
import kotlin.test.Test

class SuspendingUtilsTest {

    private val delayTime = 10L

    @Test
    fun can_watch_eagerly_while_suspending() = runTest {
        val live = mutableLiveOf("Stuff 1")
        var stuff = ""
        val watcher = live.watch(this, WatchMode.Eagerly) {
            stuff = it
        }
        delay(delayTime)
        expect(stuff).toBe("Stuff 1")
        live.value = "Stuff 2"
        delay(delayTime)
        expect(stuff).toBe("Stuff 2")
        live.value = "Stuff 3"
        delay(delayTime)
        expect(stuff).toBe("Stuff 3")
        watcher.stop()
        live.value = "Stuff 4"
        delay(delayTime)
        expect(stuff).toBe("Stuff 3")
    }

    @Test
    fun can_watch_casually_while_suspending() = runTest {
        val live = mutableLiveOf("Stuff 1")
        var stuff = ""
        val watcher = live.watch(this, WatchMode.Casually) {
            stuff = it
            delay(delayTime)
        }
        delay(delayTime)
        expect(stuff).toBe("")
        live.value = "Stuff 2"
        delay(delayTime)
        expect(stuff).toBe("Stuff 2")
        live.value = "Stuff 3"
        delay(delayTime)
        expect(stuff).toBe("Stuff 3")
        watcher.stop()
        live.value = "Stuff 4"
        delay(delayTime)
        expect(stuff).toBe("Stuff 3")
    }

    @Test
    fun can_cancel_watcher_by_canceling_job_of_an_eager_watch() = runTest {
        val live = mutableLiveOf("Stuff 1")
        var stuff = ""
        val job = watch(live, WatchMode.Eagerly) {
            stuff = it
        }
        delay(delayTime)
        expect(stuff).toBe("Stuff 1")
        live.value = "Stuff 2"
        delay(delayTime)
        expect(stuff).toBe("Stuff 2")
        live.value = "Stuff 3"
        delay(delayTime)
        expect(stuff).toBe("Stuff 3")
        job.cancel()
        live.value = "Stuff 4"
        delay(delayTime)
        expect(stuff).toBe("Stuff 3")
    }

    @Test
    fun can_cancel_watcher_by_canceling_job_of_a_casual_watcher() = runTest {
        val live = mutableLiveOf("Stuff 1")
        var stuff = ""
        val job = watch(live, WatchMode.Casually) {
            stuff = it
        }
        delay(delayTime)
        expect(stuff).toBe("")
        live.value = "Stuff 2"
        delay(delayTime)
        expect(stuff).toBe("Stuff 2")
        live.value = "Stuff 3"
        delay(delayTime)
        expect(stuff).toBe("Stuff 3")
        job.cancel()
        live.value = "Stuff 4"
        delay(delayTime)
        expect(stuff).toBe("Stuff 3")
    }
}