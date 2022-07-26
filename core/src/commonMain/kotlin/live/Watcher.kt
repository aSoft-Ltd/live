package live

import kotlin.js.JsExport

@JsExport
interface Watcher {
    fun stop()
}