package live.internal

import kollections.List
import kollections.MutableList
import kollections.iListOf
import kollections.toIList
import kollections.toIMutableList
import live.MutableLive
import live.MutableLiveList

@PublishedApi
internal class MutableLiveListWrapper<E>(
    private val live: MutableLive<List<E>>
) : AbstractLiveCollection<E>(live), MutableLiveList<E>, MutableLive<List<E>> by live {

    override fun iterator() = live.value.toMutableList().iterator()

    override fun add(element: E): Boolean = update { it.add(element) }

    override fun remove(element: E): Boolean = update { it.remove(element) }

    override fun retainAll(elements: Collection<E>): Boolean = update { it.retainAll(elements) }

    override fun removeAll(elements: Collection<E>): Boolean = update { it.removeAll(elements) }

    override fun addAll(elements: Collection<E>): Boolean = update { it.addAll(elements) }

    override fun clear() {
        live.value = iListOf()
    }

    override fun <R> update(block: (MutableList<E>) -> R): R {
        val map = live.value.toIMutableList()
        val result = block(map)
        live.value = map.toIList()
        return result
    }
}