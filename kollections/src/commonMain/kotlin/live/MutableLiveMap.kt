@file:JsExport

package live

import kollections.MutableMap
import kollections.Map
import kotlin.js.JsExport

interface MutableLiveMap<K, V> : MutableLive<Map<K, V>>, LiveMap<K, V>, MutableMap<K, V>