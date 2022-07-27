package live

import live.internal.LiveExpectationImpl

inline fun <S> expect(live: Live<S>): LiveExpectation<S> = LiveExpectationImpl(live)