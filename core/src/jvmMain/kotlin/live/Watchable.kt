package live

import functions.Callback
import koncurrent.Executor
import live.WatchMode.Eagerly
import live.WatchMode.Casually
import kotlin.jvm.JvmSynthetic

/**
 * A wrapper around a [value] that can be watched as it changes
 */
actual interface Watchable<out S> {

    actual val value: S

    /**
     * Watch the value as it changes and be updated via a [callback]
     *
     * [mode] of how you would like to watch this value. It can be [Eagerly] or [Casually]
     *
     * @return a [Watcher]
     */
    @JvmSynthetic
    actual fun watch(callback: (state: S) -> Unit, mode: WatchMode, executor: Executor): Watcher

    /**
     * Watch the value as it changes and be updated via a [function]
     *
     * [mode] of how you would like to watch this value. It can be [Eagerly] or [Casually]
     *
     * @return a [Watcher]
     */
    fun watch(function: Callback<S>, mode: WatchMode, executor: Executor): Watcher

    /**
     * Watch the value as it changes and be updated via a [callback]
     *
     * [mode] of how you would like to watch this value. It can be [Eagerly] or [Casually]
     *
     * @return a [Watcher]
     */
    @JvmSynthetic
    actual fun watch(callback: (state: S) -> Unit, mode: WatchMode): Watcher

    /**
     * Watch the value as it changes and be updated via a [function]
     *
     * [mode] of how you would like to watch this value. It can be [Eagerly] or [Casually]
     *
     * @return a [Watcher]
     */
    fun watch(function: Callback<S>, mode: WatchMode): Watcher

    /**
     * Watch the value as it changes and be updated via a [callback]
     *
     * [executor] tells in which thread should the callback be fired from
     *
     * @return a [Watcher]
     */
    @JvmSynthetic
    actual fun watch(callback: (state: S) -> Unit, executor: Executor): Watcher

    /**
     * Watch the value as it changes and be updated via a [callback]
     *
     * [executor] tells in which thread should the callback be fired from
     *
     * @return a [Watcher]
     */
    fun watch(callback: Callback<S>, executor: Executor): Watcher

    /**
     * Watch the value as it changes and be updated via a [callback]
     *
     * @return a [Watcher]
     */
    @JvmSynthetic
    actual fun watch(callback: (state: S) -> Unit): Watcher

    /**
     * Watch the value as it changes and be updated via a [callback]
     *
     * @return a [Watcher]
     */
    fun watch(callback: Callback<S>): Watcher
}