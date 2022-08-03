@file:Suppress("NON_EXPORTABLE_TYPE", "WRONG_EXPORTED_DECLARATION")

package live

import functions.Consumer
import koncurrent.Executor
import live.WatchMode.Eagerly
import live.WatchMode.Casually
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlin.jvm.JvmSynthetic

/**
 * A wrapper around a [value] that can be watched as it changes
 */
@JsExport
interface Live<out S> {

    /**
     * A history of the past values in this object.
     *
     * Note: The current value will not be available in this list
     */
    @JsName("_ignore_history")
    val history: List<S>

    val historyCapacity: Int

    val value: S


    /**
     * Subscribes to this object, to get [Live] updates of the new [value]
     *
     * @param [consumer] - The function to be executed when this [value] changes
     *
     * @param [mode] - The subscription mode on how to get values
     * [mode] of how you would like to watch this value. It can be [Eagerly] or [Casually]
     *
     * [executor] tells in which thread should the callback be fired from
     *
     * @return a [Watcher]
     */
    @JsName("_ignore_java_watch_with_mode_and_executor")
    fun watch(consumer: Consumer<@UnsafeVariance S>, mode: WatchMode, executor: Executor): Watcher

    /**
     * Watch the value as it changes and be updated via a [consumer]
     *
     * [mode] of how you would like to watch this value. It can be [Eagerly] or [Casually]
     *
     * @return a [Watcher]
     */
    @JsName("_ignore_java_watch_with_mode")
    fun watch(consumer: Consumer<@UnsafeVariance S>, mode: WatchMode): Watcher

    /**
     * Watch the value as it changes and be updated via a [consumer]
     *
     * [executor] tells in which thread should the callback be fired from
     *
     * @return a [Watcher]
     */
    @JsName("_ignore_java_watch_with_executor")
    fun watch(consumer: Consumer<@UnsafeVariance S>, executor: Executor): Watcher

    /**
     * Watch the value as it changes and be updated via a [consumer]
     *
     * @return a [Watcher]
     */
    @JsName("_ignore_java_watch")
    fun watch(consumer: Consumer<@UnsafeVariance S>): Watcher

    /**
     * Watch the value as it changes and be updated via a [callback]
     *
     * [mode] of how you would like to watch this value. It can be [Eagerly] or [Casually]
     *
     * @return a [Watcher]
     */
    @JsName("watchWithModeAndExecutor")
    @JvmSynthetic
    fun watch(callback: (state: S) -> Unit, mode: WatchMode, executor: Executor): Watcher

    /**
     * Watch the value as it changes and be updated via a [callback]
     *
     * [executor] tells in which thread should the callback be fired from
     *
     * @return a [Watcher]
     */
    @JsName("watchWithExecutor")
    @JvmSynthetic
    fun watch(callback: (state: S) -> Unit, executor: Executor): Watcher

    /**
     * Watch the value as it changes and be updated via a [callback]
     *
     * [mode] of how you would like to watch this value. It can be [Eagerly] or [Casually]
     *
     * @return a [Watcher]
     */
    @JsName("watchWithMode")
    @JvmSynthetic
    fun watch(callback: (state: S) -> Unit, mode: WatchMode): Watcher

    /**
     * Watch the value as it changes and be updated via a [callback]
     *
     * @return a [Watcher]
     */
    @JvmSynthetic
    fun watch(callback: (state: S) -> Unit): Watcher

    /**
     * Transforms this live to another live
     */
    fun <R> map(transformer: (S) -> R): Live<R>

    /**
     * Stops all [Watcher]s from watching this [Live] [value]
     */
    fun stopAll()

    companion object {
        val HISTORY_CAPACITY = 10
    }
}