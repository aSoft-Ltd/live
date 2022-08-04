@file:JvmName("LiveBuilders")
@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package live

import live.internal.MutableLiveImpl
import kotlin.js.JsExport
import kotlin.jvm.JvmName

fun <S> mutableLiveOf(
    value: S,
    capacity: Int = Live.DEFAULT_HISTORY_CAPACITY
): MutableLive<S> = MutableLiveImpl(value, capacity)

fun <S> liveOf(
    value: S
): Live<S> = MutableLiveImpl(value, 1)