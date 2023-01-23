package live.internal

import kollections.MutableSet
import kollections.Set
import kollections.iSetOf
import kollections.toIMutableSet
import kollections.toISet
import live.MutableLive
import live.MutableLiveSet

@PublishedApi
internal class MutableLiveSetWrapper<E>(
    private val live: MutableLive<Set<E>>
) : AbstractLiveCollection<E>(live), MutableLiveSet<E>, MutableLive<Set<E>> by live{

    override fun iterator() = live.value.toMutableList().iterator()

    override fun add(element: E): Boolean = update { it.add(element) }

    override fun remove(element: E): Boolean = update { it.remove(element) }

    override fun retainAll(elements: Collection<E>): Boolean = update { it.retainAll(elements) }

    override fun removeAll(elements: Collection<E>): Boolean = update { it.removeAll(elements) }

    override fun addAll(elements: Collection<E>): Boolean = update { it.addAll(elements) }

    override fun clear() {
        live.value = iSetOf()
    }

    override fun <R> update(block: (MutableSet<E>) -> R): R {
        val map = live.value.toIMutableSet()
        val result = block(map)
        live.value = map.toISet()
        return result
    }
}