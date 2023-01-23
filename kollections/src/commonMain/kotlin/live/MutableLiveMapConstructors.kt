@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package live

import kollections.MapEntry
import kollections.iMapOf
import live.internal.MutableLiveMapWrapper
import kotlin.js.JsExport
import kotlin.js.JsName

@JsName("emptyMutableLiveMapOf")
fun <K, V> mutableLiveMapOf(): MutableLiveMap<K, V> = MutableLiveMapWrapper(mutableLiveOf(iMapOf()))

fun <K, V> mutableLiveMapOf(pairs: MapEntry<K, V>): MutableLiveMap<K, V> = MutableLiveMapWrapper(mutableLiveOf(iMapOf(pairs)))