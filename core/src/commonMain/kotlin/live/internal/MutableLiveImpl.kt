package live.internal

import koncurrent.Executor
import koncurrent.SynchronousExecutor
import live.*
import kotlin.math.max
import kotlin.math.min

internal class MutableLiveImpl<S>(
    state: S,
    capacity: Int
) : AbstractLive<S>(), MutableLive<S> {

    override val historyCapacity: Int = if (capacity < 0) 0 else capacity

    override val history = mutableListOf<S>()

    private var historyCursor = 0

    override var value: S = state
        set(value) {
            archiveToHistory(field)
            field = value
            dispatch(value)
        }

    private fun archiveToHistory(v: S) {
        while (history.size > 0 && history.size > historyCapacity) {
            history.removeAt(0)
        }
        if (historyCursor == 0) {
            if (history.size == historyCapacity) {
                history.removeAt(0)
            }
            history.add(v)
        }
    }

    private fun dispatch(value: S) {
        for (watcher in watchers) watcher.execute(value)
        for (item in mapQueue) item.emit(value)
    }

    override fun undo() {
        if (history.size > 0 && historyCursor <= historyCapacity) {
            historyCursor++
            val historyIndex = max(0, history.size - historyCursor)
            val v = history[historyIndex]
            if (historyCursor == 1) {
                historyCursor++
                history.add(value)
            }
            value = v
        }
    }

    override fun redo() {
        if (history.size > 0 && historyCursor > 0) {
            historyCursor--
            val historyIndex = min(history.size - 1, history.size - historyCursor)
            val v = history[historyIndex]
            var historyCursorHasBeenIncremented = false
            if (historyCursor == 0) {
                historyCursorHasBeenIncremented = true
                historyCursor++
            }
            value = v
            if (historyCursorHasBeenIncremented) {
                historyCursor--
            }
        }
    }

    private val watchers = mutableListOf<WatcherImpl<S>>()

    override fun stopAll() {
        watchers.clear()
        mapQueue.clear()
    }

    override fun watchRaw(callback: ((state: S) -> Unit)?, mode: WatchMode?, executor: Executor?): Watcher {
        val cb = callback ?: throw IllegalStateException("A callback to a live object must not be null or undefined")
        val md = mode ?: WatchMode.Default
        val ex = executor ?: SynchronousExecutor
        val watcher = WatcherImpl(watchers, ex, cb)
        watchers.add(watcher)
        if (md == WatchMode.Eagerly) watcher.execute(value)
        return watcher
    }

    override fun toString() = "Live(value=$value)"
}