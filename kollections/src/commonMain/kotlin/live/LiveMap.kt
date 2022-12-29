@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package live

import kollections.Map
import kotlin.js.JsExport

interface LiveMap<K, V> : Live<Map<K, V>>