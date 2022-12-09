@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION", "NON_EXPORTABLE_TYPE")

package live

import functions.Function
import live.internal.MutableLiveImpl
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

interface MutableLive<S> : Live<S> {
    override val history: MutableList<S>

    override var value: S

    fun undo()

    fun redo()

    /**
     * Transforms this live to another live
     */
    @JvmSynthetic
    override fun <R> map(transformer: (S) -> R): MutableLive<R>

    override fun <R> map(transformer: Function<S, R>): MutableLive<R>

    /**
     * Notify the watchers without changing the underlying value
     */
    fun dispatch(value: S = this.value)

    companion object {
        @JvmStatic
        @JvmOverloads
        fun <S> of(value: S, capacity: Int = Live.DEFAULT_HISTORY_CAPACITY): MutableLive<S> = MutableLiveImpl(value, capacity)
    }
}