package live

import androidx.compose.runtime.*

@Composable
inline fun <S> Live<S>.watchAsState(): S {
    var state by remember { mutableStateOf(value) }
    DisposableEffect(this) {
        val watcher = watch(WatchMode.CASUALLY) { state = it }
        onDispose { watcher.stop() }
    }
    return state
}