@file:JsExport @file:Suppress("NON_EXPORTABLE_TYPE")

import koncurrent.Executor
import koncurrent.SynchronousExecutor
import live.Live
import live.WatchMode
import live.watch
import react.useEffect
import react.useEffectOnce
import react.useState

fun <S> useNullableLive(live: Live<S>?): S? = useNullableLiveWithExecutor(live, SynchronousExecutor)

fun <S> useNullableLiveWithExecutor(live: Live<S>?, executor: Executor): S? {
    var state by useState(live?.value)
    useEffect(live, executor) {
        val mode = if (state == null) WatchMode.Eagerly else WatchMode.Casually
        val watcher = live?.watch(mode = mode, executor) { state = it }
        if (watcher != null) cleanup { watcher.stop() }
    }
    return state
}

fun <S> useLive(live: Live<S>) = useLiveWithExecutor(live, SynchronousExecutor)

fun <S> useLiveWithExecutor(live: Live<S>, executor: Executor): S {
    var state by useState(live.value)
    useEffectOnce {
        val watcher = live.watch(mode = WatchMode.Casually, executor) { state = it }
        cleanup { watcher.stop() }
    }
    return state
}