@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION", "NON_EXPORTABLE_TYPE")

package live

import live.internal.MutableLiveImpl
import kotlin.js.JsExport
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic

interface MutableLive<S> : Live<S> {
    override val history: MutableList<S>

    override var value: S

    fun undo()

    fun redo()

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