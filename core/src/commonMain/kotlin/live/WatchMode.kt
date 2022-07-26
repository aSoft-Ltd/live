@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION")

package live

import kotlin.js.JsExport
import kotlin.jvm.JvmField

/**
 * A Mode on which one can watch a [Live] object
 */
enum class WatchMode {
    /**
     * A mode of watching a live object without skipping even a single value
     */
    Eagerly,

    /**
     * A mode of watching a live object skipping the current value that is already available in the live object
     */
    Casually;

    companion object {
        @JvmField
        val Default: WatchMode = Casually
    }
}