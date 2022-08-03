package live

import koncurrent.SynchronousExecutor
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow


fun <S> Live<S>.toFlow(): Flow<S> = callbackFlow {
    val watcher = watch(WatchMode.Eagerly, SynchronousExecutor) { trySend(it) }
    awaitClose { watcher.stop() }
}