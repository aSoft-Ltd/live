@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package live

import kollections.MapEntry
import kollections.iListOf
import kollections.iMapOf
import kollections.iSetOf
import kollections.toISet
import live.internal.MutableLiveListWrapper
import live.internal.MutableLiveMapWrapper
import live.internal.MutableLiveSetWrapper
import kotlin.js.JsExport
import kotlin.js.JsName

// ----------------------------------- MutableLiveList constructors ----------------------------------
@JsName("emptyMutableLiveListOF")
fun <E> mutableLiveListOf(): MutableLiveList<E> = MutableLiveListWrapper(mutableLiveOf(iListOf()))

fun <E> mutableLiveListOf(vararg elements: E): MutableLiveList<E> = MutableLiveListWrapper(mutableLiveOf(iListOf(*elements)))


// ----------------------------------- MutableLiveSet constructors ----------------------------------
@JsName("emptyMutableLiveSetOf")
fun <E> mutableLiveSetOf(): MutableLiveSet<E> = MutableLiveSetWrapper(mutableLiveOf(iSetOf()))

@JsName("_ignore_mutableLiveSetOf")
fun <E> mutableLiveSetOf(col: Collection<E>): MutableLiveSet<E> = MutableLiveSetWrapper(mutableLiveOf(col.toISet()))

fun <E> mutableLiveSetOf(vararg elements: E): MutableLiveSet<E> = MutableLiveSetWrapper(mutableLiveOf(iSetOf(*elements)))


// ----------------------------------- MutableLiveMap constructors ----------------------------------
@JsName("emptyMutableLiveMapOf")
fun <K, V> mutableLiveMapOf(): MutableLiveMap<K, V> = MutableLiveMapWrapper(mutableLiveOf(iMapOf()))

fun <K, V> mutableLiveMapOf(pairs: MapEntry<K, V>): MutableLiveMap<K, V> = MutableLiveMapWrapper(mutableLiveOf(iMapOf(pairs)))
