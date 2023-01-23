@file:JsExport

package live

import kollections.Map
import kollections.MapLike
import kotlin.js.JsExport

interface LiveMap<K, V> : MapLike<K, V>, Live<Map<K, V>>