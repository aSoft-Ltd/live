package live.internal

import kollections.Collection
import kollections.internal.AbstractCollection
import live.Live

internal abstract class AbstractLiveCollection<E>(
    private val live: Live<Collection<E>>
) : AbstractCollection<E>(), Collection<E> by live.value {

    override val size get() = live.value.size

    override fun isEmpty(): Boolean = live.value.isEmpty()

    override fun contains(element: E): Boolean = live.value.contains(element)
}