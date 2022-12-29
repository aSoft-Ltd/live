package live.internal

import functions.Callback
import koncurrent.Executor
import live.WatchMode
import live.Watchable
import live.Watcher

internal actual abstract class AbstractWatchable<out S> : Watchable<S> {

    protected actual abstract fun watchRaw(callback: ((state: S) -> Unit)?, mode: WatchMode?, executor: Executor?): Watcher

    actual override fun watch(callback: (state: S) -> Unit, mode: WatchMode, executor: Executor): Watcher = watchRaw(callback, mode, executor)

    actual override fun watch(callback: (state: S) -> Unit, mode: WatchMode): Watcher = watchRaw(callback, mode, null)

    actual override fun watch(callback: (state: S) -> Unit): Watcher = watchRaw(callback, null, null)

    actual override fun watch(callback: (state: S) -> Unit, executor: Executor) = watchRaw(callback, null, executor)

    override fun watch(function: Callback<S>, mode: WatchMode, executor: Executor) = watchRaw(function::invoke, mode, executor)

    override fun watch(callback: Callback<S>, executor: Executor) = watchRaw(callback::invoke, null, null)

    override fun watch(function: Callback<S>, mode: WatchMode) = watchRaw(function::invoke, null, null)

    override fun watch(callback: Callback<S>) = watchRaw(callback::invoke, null, null)
}