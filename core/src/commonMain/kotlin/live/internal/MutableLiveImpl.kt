package live.internal

import koncurrent.Executor
import koncurrent.SynchronousExecutor
import live.*

internal class MutableLiveImpl<S>(state: S) : AbstractLive<S>(), MutableLive<S> {
    override var value: S = state
        set(value) {
            field = value
            for (watcher in watchers) watcher.execute(value)
        }

    private val watchers = mutableListOf<WatcherImpl<S>>()

    override fun stopAll() = watchers.clear()

    override fun watchRaw(callback: ((state: S) -> Unit)?, mode: WatchMode?, executor: Executor?): Watcher {
        val cb = callback ?: throw IllegalStateException("A callback to a live object must not be null or undefined")
        val md = mode ?: WatchMode.Default
        val ex = executor ?: SynchronousExecutor
        val watcher = WatcherImpl(watchers, ex, cb)
        watchers.add(watcher)
        if (md == WatchMode.Eagerly) watcher.execute(value)
        return watcher
    }
}