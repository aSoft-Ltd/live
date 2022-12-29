package live

import functions.Function1I1R
import live.internal.DEFAULT_HISTORY_CAPACITY
import live.internal.MutableLiveImpl
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

actual interface MutableLive<S> : Live<S> {
    actual override val history: MutableList<S>

    actual override var value: S

    actual fun undo()

    actual fun redo()

    /**
     * Transforms this live to another live
     */
    @JvmSynthetic
    actual override fun <R> map(transformer: (S) -> R): MutableLive<R>

    override fun <R> map(transformer: Function1I1R<S, R>): MutableLive<R>

    /**
     * Notify the watchers without changing the underlying value
     */
    actual fun dispatch(value: S)

    actual fun dispatch()

    companion object {
        @JvmStatic
        @JvmOverloads
        fun <S> of(value: S, capacity: Int = DEFAULT_HISTORY_CAPACITY): MutableLive<S> = mutableLiveOf(value, capacity)
    }
}