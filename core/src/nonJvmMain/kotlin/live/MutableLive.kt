@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package live

import kotlin.js.JsExport
import kotlin.js.JsName
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

    /**
     * Notify the watchers without changing the underlying value
     */
    @JsName("dispatchValue")
    actual fun dispatch(value: S)

    actual fun dispatch()
}