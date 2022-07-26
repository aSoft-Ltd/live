package live

import koncurrent.Executor

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