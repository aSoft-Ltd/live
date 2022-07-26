package live

import koncurrent.coroutines.asExecutor
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

fun <S> Live<S>.watchAsFlow(mode: WatchMode = WatchMode.Eagerly): Flow<S> = callbackFlow {
    val watcher = watch(mode, asExecutor()) { trySend(it) }
    awaitClose { watcher.stop() }
}