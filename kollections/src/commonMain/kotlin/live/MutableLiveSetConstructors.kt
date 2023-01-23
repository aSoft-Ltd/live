@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package live

import kollections.iSetOf
import live.internal.MutableLiveSetWrapper
import kotlin.js.JsExport
import kotlin.js.JsName

@JsName("emptyMutableLiveSetOF")
fun <E> mutableLiveSetOf(): MutableLiveSet<E> = MutableLiveSetWrapper(mutableLiveOf(iSetOf()))

fun <E> mutableLiveSetOf(vararg elements: E): MutableLiveSet<E> = MutableLiveSetWrapper(mutableLiveOf(iSetOf(*elements)))