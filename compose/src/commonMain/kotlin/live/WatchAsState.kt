package live

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.DisposableEffect

@Composable
fun <S> Live<S>.watchAsState(): S {
    var state by remember { mutableStateOf(value) }
    DisposableEffect(this) {
        val watcher = watch(WatchMode.Casually) { state = it }
        onDispose { watcher.stop() }
    }
    return state
}