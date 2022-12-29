package live.internal

import live.Live
import live.MutableLive
import live.mutableLiveOf

internal abstract class AbstractLive<S> : AbstractLiveMonoid<S>(), Live<S> {

    val mapQueue = mutableListOf<LiveQueueItem<S, Any?>>()

    override fun <R> map(transformer: (S) -> R): MutableLive<R> {
        val live = mutableLiveOf(transformer(value))
        val item = LiveQueueItem(live, transformer)
        mapQueue.add(item)
        return live
    }
}