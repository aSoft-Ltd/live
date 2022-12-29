@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package live

import kollections.Map
import kotlin.js.JsExport

interface MutableLiveMap<K, V> : MutableLive<Map<K, V>>, LiveMap<K, V> {
    operator fun set(key: K, value: V): V?
    fun remove(key: K): V?
}