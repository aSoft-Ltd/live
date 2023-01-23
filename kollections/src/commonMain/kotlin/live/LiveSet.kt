@file:JsExport

package live

import kollections.CollectionLike
import kollections.Set
import kotlin.js.JsExport

interface LiveSet<out E> : CollectionLike<E>, Live<Set<E>>