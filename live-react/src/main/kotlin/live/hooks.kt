package live

import react.*

@JsExport
@JsName("useLive")
fun <S> useLive(live: Live<S>): S {
    val (_, setState) = rawUseState(live.value)
    rawUseEffect({
        val listener = live.watch { (setState as RSetState<S>)(it) }
        val cleanup: () -> Unit = { listener.stop() }
        cleanup
    }, emptyArray())
    return live.value
}