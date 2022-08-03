import expect.expect
import kotlinx.coroutines.*
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import live.*
import kotlin.test.Test

class SuspendingUtilsTest {

    @Test
    fun can_collect_from_state_flow_while_watching_live() = runTest {
        val live = mutableLiveOf("Stuff 1")
        val scope = CoroutineScope(coroutineContext + SupervisorJob())
        val sf = live.toFlow()
        var stuff = ""
        scope.launch {
            sf.collect {
                stuff = it
            }
        }
        runCurrent()
        expect(stuff).toBe("Stuff 1")
        live.value = "Stuff 2"
        runCurrent()
        expect(stuff).toBe("Stuff 2")
        live.value = "Stuff 3"
        runCurrent()
        expect(stuff).toBe("Stuff 3")
        scope.cancel()
        runCurrent()
        live.value = "Stuff 4"
        live.stopAll()
        expect(stuff).toBe("Stuff 3")
    }
}