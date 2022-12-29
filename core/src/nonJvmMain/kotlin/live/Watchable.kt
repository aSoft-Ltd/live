@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package live

import koncurrent.Executor
import live.WatchMode.Casually
import live.WatchMode.Eagerly
import kotlin.js.JsExport
import kotlin.js.JsName

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
    @JsName("watchWithModeAndExecutor")
    actual fun watch(callback: (state: S) -> Unit, mode: WatchMode, executor: Executor): Watcher

    /**
     * Watch the value as it changes and be updated via a [callback]
     *
     * [mode] of how you would like to watch this value. It can be [Eagerly] or [Casually]
     *
     * @return a [Watcher]
     */
    @JsName("watchWithMode")
    actual fun watch(callback: (state: S) -> Unit, mode: WatchMode): Watcher

    /**
     * Watch the value as it changes and be updated via a [callback]
     *
     * [executor] tells in which thread should the callback be fired from
     *
     * @return a [Watcher]
     */
    @JsName("watchWithExecutor")
    actual fun watch(callback: (state: S) -> Unit, executor: Executor): Watcher

    /**
     * Watch the value as it changes and be updated via a [callback]
     *
     * @return a [Watcher]
     */
    actual fun watch(callback: (state: S) -> Unit): Watcher
}