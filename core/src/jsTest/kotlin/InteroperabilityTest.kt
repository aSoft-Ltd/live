import expect.expect
import koncurrent.MockExecutor
import koncurrent.Promise
import koncurrent.setTimeout
import live.WatchMode
import live.liveOf
import live.mutableLiveOf
import kotlin.test.Test

class InteroperabilityTest {

    val executor = MockExecutor()

    @Test
    fun should_be_able_to_get_a_live_value() {
        val live = mutableLiveOf(123)
        globalThis.live = live
        val value: Int = globalThis.live.value
        expect(value).toBe(123)
    }

    @Test
    fun should_be_able_to_watch_a_live_casually() {
        val pLive = mutableLiveOf(1234)
        globalThis.live = pLive
        val live = globalThis.live
        var number = 0
        val func = js("""function(x){ number=x; console.log('Watching '+x); }""")
        live.watchWithModeAndExecutor(func, WatchMode.Casually, executor)
        expect(number).toBe(0)
        live.value = 456
        expect(number).toBe(456)
        live.value = 789

        val value: Int = live.value
        expect(value).toBe(789)
    }

    @Test
    fun should_be_able_to_watch_a_live_eagerly() {
        val pLive = liveOf(1234)
        globalThis.live = pLive
        val live = globalThis.live
        var number: Int = 0
        val func = js("""function(x){ number=x; console.log('Watching '+x); }""")
        live.watchWithModeAndExecutor(func, WatchMode.Eagerly, executor)
        expect(number).toBe(1234)
        live.value = 456
        live.value = 789
    }

    @Test
    fun should_be_able_to_watch_without_passing_watch_mode() {
        val pLive = liveOf(1234)
        globalThis.live = pLive
        val live = globalThis.live
        var number: Int = 0
        val func = js("""function(x){ number=x; console.log('Watching '+x); }""")
        live.watch(func)
        live.value = 456
        live.value = 789

        return Promise { res, _ ->
            setTimeout({ res(0) }, 500)
        }.unsafeCast<Unit>()
    }
}