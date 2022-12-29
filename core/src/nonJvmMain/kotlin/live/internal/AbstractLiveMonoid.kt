package live.internal

import live.MutableLive

internal actual abstract class AbstractLiveMonoid<S> : AbstractWatchable<S>(), MutableLive<S>