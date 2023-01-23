@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package live

import kollections.MutableCollectionLike
import kollections.MutableList
import kotlin.js.JsExport

interface MutableLiveList<E> : MutableCollectionLike<E>, LiveList<E> {
    fun <R> update(block: (MutableList<E>) -> R): R
}