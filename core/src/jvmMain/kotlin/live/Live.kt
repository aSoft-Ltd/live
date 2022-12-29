@file:Suppress("NON_EXPORTABLE_TYPE", "WRONG_EXPORTED_DECLARATION")

package live

import functions.Function1I1R
import kotlin.jvm.JvmSynthetic

/**
 * A wrapper around a [value] that can be watched as it changes
 */
actual interface Live<out S> : Watchable<S> {

    /**
     * A history of the past values in this object.
     *
     * Note: The current value will not be available in this list
     */
    actual val history: List<S>

    actual val historyCapacity: Int

    /**
     * The current [value] of the [Live] object
     */
    actual override val value: S

    /**
     * Transforms this live to another live
     */
    @JvmSynthetic
    actual fun <R> map(transformer: (S) -> R): Live<R>

    fun <R> map(transformer: Function1I1R<S, R>): Live<R>

    /**
     * Stops all [Watcher]s from watching this [Live] [value]
     */
    actual fun stopAll()
}