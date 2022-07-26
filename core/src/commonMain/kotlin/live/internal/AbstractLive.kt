package live.internal

import functions.Consumer
import koncurrent.Executor
import live.Live
import live.WatchMode
import live.Watcher
import live.mutableLiveOf

internal abstract class AbstractLive<out S> : Live<S> {

    protected abstract fun watchRaw(callback: ((state: S) -> Unit)?, mode: WatchMode?, executor: Executor?): Watcher
    override fun watch(callback: (state: S) -> Unit, mode: WatchMode, executor: Executor): Watcher = watchRaw(callback, mode, executor)

    override fun watch(callback: (state: S) -> Unit, mode: WatchMode): Watcher = watchRaw(callback, mode, null)

    override fun watch(callback: (state: S) -> Unit): Watcher = watchRaw(callback, null, null)

    override fun watch(callback: (state: S) -> Unit, executor: Executor) = watchRaw(callback, null, executor)

    override fun watch(consumer: Consumer<@UnsafeVariance S>) = watchRaw(consumer::accept, null, null)

    override fun watch(consumer: Consumer<@UnsafeVariance S>, executor: Executor) = watchRaw(consumer::accept, null, null)

    override fun watch(consumer: Consumer<@UnsafeVariance S>, mode: WatchMode) = watchRaw(consumer::accept, null, null)

    override fun watch(consumer: Consumer<@UnsafeVariance S>, mode: WatchMode, executor: Executor) = watchRaw(consumer::accept, mode, executor)

    override fun <R> map(transformer: (S) -> R): Live<R> {
        val live = mutableLiveOf(transformer(value))
        watch { live.value = transformer(it) }
        return live
    }
}