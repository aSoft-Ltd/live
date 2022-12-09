package live.internal

import functions.Function
import live.Live
import live.MutableLive
import live.mutableLiveOf

internal abstract class AbstractLive<S> : AbstractWatchable<S>(), Live<S> {

    val mapQueue = mutableListOf<LiveQueueItem<S, Any?>>()

    override fun <R> map(transformer: Function<S, R>): MutableLive<R> = map(transformer::invoke)

    override fun <R> map(transformer: (S) -> R): MutableLive<R> {
        val live = mutableLiveOf(transformer(value))
        val item = LiveQueueItem(live, transformer)
        mapQueue.add(item)
        return live
    }
}