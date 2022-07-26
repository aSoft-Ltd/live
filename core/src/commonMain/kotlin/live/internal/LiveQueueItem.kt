package live.internal

import live.MutableLive

class LiveQueueItem<in I, O>(
    val live: MutableLive<O>,
    val transformer: (I) -> O
) {
    fun emit(value: I) {
        live.value = transformer(value)
    }
}