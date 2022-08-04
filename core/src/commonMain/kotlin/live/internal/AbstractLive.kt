package live.internal

import functions.Callback
import functions.Function
import koncurrent.Executor
import live.Live
import live.WatchMode
import live.Watcher
import live.mutableLiveOf

internal abstract class AbstractLive<S> : Live<S> {

    val mapQueue = mutableListOf<LiveQueueItem<S, Any?>>()

    protected abstract fun watchRaw(callback: ((state: S) -> Unit)?, mode: WatchMode?, executor: Executor?): Watcher

    override fun watch(callback: (state: S) -> Unit, mode: WatchMode, executor: Executor): Watcher = watchRaw(callback, mode, executor)

    override fun watch(callback: (state: S) -> Unit, mode: WatchMode): Watcher = watchRaw(callback, mode, null)

    override fun watch(callback: (state: S) -> Unit): Watcher = watchRaw(callback, null, null)

    override fun watch(callback: (state: S) -> Unit, executor: Executor) = watchRaw(callback, null, executor)

    override fun watch(Callback: Callback<S>) = watchRaw(Callback::invoke, null, null)

    override fun watch(Callback: Callback<S>, executor: Executor) = watchRaw(Callback::invoke, null, null)

    override fun watch(Callback: Callback<S>, mode: WatchMode) = watchRaw(Callback::invoke, null, null)

    override fun watch(Callback: Callback<S>, mode: WatchMode, executor: Executor) = watchRaw(Callback::invoke, mode, executor)

    override fun <R> map(transformer: Function<S, R>): Live<R> = map(transformer::invoke)

    override fun <R> map(transformer: (S) -> R): Live<R> {
        val live = mutableLiveOf(transformer(value))
        val item = LiveQueueItem(live, transformer)
        mapQueue.add(item)
        return live
    }
}