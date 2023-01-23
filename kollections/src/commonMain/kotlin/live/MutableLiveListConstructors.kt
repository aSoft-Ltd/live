@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package live

import kollections.iListOf
import live.internal.MutableLiveListWrapper
import kotlin.js.JsExport
import kotlin.js.JsName

@JsName("emptyMutableLiveListOF")
fun <E> mutableLiveListOf(): MutableLiveList<E> = MutableLiveListWrapper(mutableLiveOf(iListOf()))

fun <E> mutableLiveListOf(vararg elements: E): MutableLiveList<E> = MutableLiveListWrapper(mutableLiveOf(iListOf(*elements)))