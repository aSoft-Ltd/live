@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package live

import kollections.MutableMap
import kollections.MutableMapLike
import kotlin.js.JsExport

interface MutableLiveMap<K, V> : MutableMapLike<K, V>, LiveMap<K, V> {
    fun <R> update(block: (MutableMap<K, V>) -> R): R
}