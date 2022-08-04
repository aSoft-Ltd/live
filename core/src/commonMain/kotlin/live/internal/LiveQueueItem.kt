package live.internal

import live.MutableLive

internal class LiveQueueItem<in I, out O>(
    val live: MutableLive<@UnsafeVariance O>,
    val transformer: (I) -> O
) {
    fun emit(value: I) {
        live.value = transformer(value)
    }
}