package live

import koncurrent.Executor
import live.internal.CompoundWatcher

inline fun <S> Live<S>.watch(
    mode: WatchMode,
    executor: Executor,
    noinline callback: (state: S) -> Unit
): Watcher = watch(callback, mode, executor)

inline fun <S> Live<S>.watch(
    mode: WatchMode,
    noinline callback: (state: S) -> Unit
): Watcher = watch(callback, mode)

inline fun <S> Live<S>.watch(
    executor: Executor,
    noinline callback: (state: S) -> Unit
): Watcher = watch(callback, executor)

inline fun <S> Live<S>.watch(
    noinline callback: (state: S) -> Unit
): Watcher = watch(callback)

fun <S1, S2> watch(live1: Live<S1>, live2: Live<S2>, callback: (S1, S2) -> Unit): Watcher {
    val watcher1 = live1.watch {
        callback(it, live2.value)
    }
    val watcher2 = live2.watch {
        callback(live1.value, it)
    }
    return CompoundWatcher(setOf(watcher1, watcher2))
}