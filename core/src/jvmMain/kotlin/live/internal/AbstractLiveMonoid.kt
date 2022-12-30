package live.internal

import functions.Function1
import live.MutableLive

internal actual abstract class AbstractLiveMonoid<S> : AbstractWatchable<S>(), MutableLive<S> {
    override fun <R> map(transformer: Function1<S, R>): MutableLive<R> = map(transformer::invoke)
}