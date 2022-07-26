@file:Suppress("NON_EXPORTABLE_TYPE")

import koncurrent.Executor
import live.Live
import live.WatchMode
import live.watch
import react.useEffectOnce
import react.useState

@JsExport
fun <S> useLive(live: Live<S>, executor: Executor? = null): S {
    var state by useState(live.value)
    useEffectOnce {
        val watcher = if (executor != null) {
            live.watch(mode = WatchMode.Casually, executor) { state = it }
        } else {
            live.watch(mode = WatchMode.Casually) { state = it }
        }
        cleanup { watcher.stop() }
    }
    return state
}