@file:JvmName("LiveBuilders")
@file:JsExport

package live

import live.internal.MutableLiveImpl
import kotlin.js.JsExport
import kotlin.jvm.JvmName

fun <S> mutableLiveOf(value: S): MutableLive<S> = MutableLiveImpl(value)

fun <S> liveOf(value: S): Live<S> = MutableLiveImpl(value)