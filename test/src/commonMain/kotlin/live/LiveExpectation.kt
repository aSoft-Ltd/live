package live

import expect.CollectionExpectation

interface LiveExpectation<out S> : CollectionExpectation<S> {

    fun toBeIn(state: @UnsafeVariance S)

    fun toHaveGoneThrough(vararg states: @UnsafeVariance S): List<S>
}