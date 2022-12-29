package live.internal

import functions.Function1I1R
import live.Live
import live.MutableLive
import live.mutableLiveOf

internal actual abstract class AbstractLiveMonoid<S> : AbstractWatchable<S>(), MutableLive<S> {
    override fun <R> map(transformer: Function1I1R<S, R>): MutableLive<R> = map(transformer::invoke)
}