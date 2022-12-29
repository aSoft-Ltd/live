package live.internal

import koncurrent.Executor
import live.WatchMode
import live.Watchable
import live.Watcher

internal expect abstract class AbstractWatchable<out S>() : Watchable<S> {

    protected abstract fun watchRaw(callback: ((state: S) -> Unit)?, mode: WatchMode?, executor: Executor?): Watcher

    override fun watch(callback: (state: S) -> Unit, mode: WatchMode, executor: Executor): Watcher

    override fun watch(callback: (state: S) -> Unit, executor: Executor): Watcher

    override fun watch(callback: (state: S) -> Unit, mode: WatchMode): Watcher

    override fun watch(callback: (state: S) -> Unit): Watcher
}