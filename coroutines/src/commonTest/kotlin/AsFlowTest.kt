import expect.expect
import expect.toBe
import kotlinx.coroutines.*
import kotlinx.coroutines.test.runTest
import live.mutableLiveOf
import live.toFlow
import kotlin.test.Test

class AsFlowTest {
    @Test
    fun should_wait_for_values() = runTest {
        val live = mutableLiveOf(-1)
        val scope = CoroutineScope(SupervisorJob())
        launch {
            repeat(4) {
                live.value = it
                delay(1000)
            }
            scope.cancel()
        }
        try {
            withContext(scope.coroutineContext) {
                live.toFlow().collect {
                    println(it)
                }
            }
        } catch (err: Throwable) {
            expect(err).toBe<CancellationException>()
        }
    }
}