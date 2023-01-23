@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package live

import kollections.MutableCollectionLike
import kollections.MutableSet
import kotlin.js.JsExport

interface MutableLiveSet<E> : MutableCollectionLike<E>, LiveSet<E> {
    fun <R> update(block: (MutableSet<E>) -> R): R
}