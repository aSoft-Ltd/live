@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package live

import kollections.MutableCollectionLike
import kollections.MutableSet
import kollections.Set
import kotlin.js.JsExport

interface MutableLiveSet<E> : MutableCollectionLike<E>, MutableLive<Set<E>>, LiveSet<E> {
    fun <R> update(block: (MutableSet<E>) -> R): R
}