package live

import expect.CollectionExpectation
import kotlin.reflect.KClass

interface LiveExpectation<out S> : CollectionExpectation<S> {

    fun toBeIn(state: @UnsafeVariance S)

    fun toHaveGoneThrough(vararg states: @UnsafeVariance S): List<S>

    fun toHaveGoneThrough(vararg states: KClass<*>): List<S>
}