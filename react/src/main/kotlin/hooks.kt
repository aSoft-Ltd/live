@file:Suppress("NON_EXPORTABLE_TYPE")

import koncurrent.Executor
import koncurrent.Executors
import koncurrent.SynchronousExecutor
import live.Live
import live.WatchMode
import live.watch
import react.useEffectOnce
import react.useState

@JsExport
fun <S> useLive(live: Live<S>) = useLiveWithExecutor(live, SynchronousExecutor)

@JsExport
fun <S> useLiveWithExecutor(live: Live<S>, executor: Executor): S {
    var state by useState(live.value)
    useEffectOnce {
        val watcher = live.watch(mode = WatchMode.Casually, executor) { state = it }
        cleanup { watcher.stop() }
    }
    return state
}