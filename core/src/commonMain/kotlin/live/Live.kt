@file:Suppress("NON_EXPORTABLE_TYPE", "WRONG_EXPORTED_DECLARATION")

package live

import functions.Function
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlin.jvm.JvmSynthetic

/**
 * A wrapper around a [value] that can be watched as it changes
 */
@JsExport
interface Live<out S> : Watchable<S> {

    /**
     * A history of the past values in this object.
     *
     * Note: The current value will not be available in this list
     */
    @JsName("_ignore_history")
    val history: List<S>

    @JsName("_ignore_history_capacity")
    val historyCapacity: Int

    /**
     * The current [value] of the [Live] object
     */
    override val value: S

    /**
     * Transforms this live to another live
     */
    @JvmSynthetic
    fun <R> map(transformer: (S) -> R): Live<R>

    @JsName("_ignore_map")
    fun <R> map(transformer: Function<S, R>): Live<R>

    /**
     * Stops all [Watcher]s from watching this [Live] [value]
     */
    fun stopAll()

    companion object {
        val DEFAULT_HISTORY_CAPACITY = 10
    }
}