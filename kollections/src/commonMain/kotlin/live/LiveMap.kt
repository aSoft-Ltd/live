@file:JsExport

package live

import kollections.Map
import kotlin.js.JsExport

interface LiveMap<K, V> : Live<Map<K, V>>, Map<K, V>