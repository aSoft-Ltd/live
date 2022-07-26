package live

import koncurrent.coroutines.asExecutor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun <S> Live<S>.watch(
    scope: CoroutineScope,
    mode: WatchMode = WatchMode.Eagerly,
    callback: suspend CoroutineScope.(S) -> Unit
) = watch(mode, scope.asExecutor()) {
    scope.launch { callback(it) }
}

fun <S> CoroutineScope.watch(live: Live<S>, mode: WatchMode = WatchMode.Eagerly, callback: suspend CoroutineScope.(S) -> Unit): Job = launch {
    live.watchAsFlow(mode).collect { callback(it) }
}