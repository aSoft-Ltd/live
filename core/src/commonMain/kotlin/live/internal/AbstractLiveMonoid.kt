package live.internal

import live.MutableLive

internal expect abstract class AbstractLiveMonoid<S>() : AbstractWatchable<S>, MutableLive<S>