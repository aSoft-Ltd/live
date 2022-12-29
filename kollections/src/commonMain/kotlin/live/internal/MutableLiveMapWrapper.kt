package live.internal

import kollections.*
import live.MutableLive
import live.MutableLiveMap
import kotlin.collections.MutableMap as KMutableMap
import kotlin.collections.Collection as KCollection
import kotlin.collections.Map as KMap
import kollections.internal.AbstractCollection

internal class MutableLiveMapWrapper<K, V>(
    private val live: MutableLive<Map<K, V>>
) : AbstractCollection<MapEntry<K, V>>(), MutableLiveMap<K, V>, MutableLive<Map<K, V>> by live {
    override fun containsAll(elements: KCollection<MapEntry<K, V>>): Boolean = live.value.containsAll(elements)

    override val size get() = live.value.size
    override val keys get() = live.value.keys.toIMutableSet()
    override val values get() = live.value.values.toIMutableList()
    override val pairs get() = live.value.pairs

    override fun get(key: K): V? = live.value[key]

    override fun getValue(key: K): V = live.value.getValue(key)

    override val entries get() = live.value.toMutableMap().entries

    override fun isEmpty(): Boolean = live.value.isEmpty()

    override fun contains(element: MapEntry<K, V>): Boolean = live.value.contains(element)

    override fun iterator(): Iterator<MapEntry<K, V>> = live.value.iterator()

    override fun clear() {
        live.value = iMapOf()
    }

    override fun remove(key: K): V? = mutateAndNotify { it.remove(key) }

    override fun put(key: K, value: V): V? = mutateAndNotify { it.put(key, value) }

    override fun putAll(from: KMap<out K, V>) = mutateAndNotify { it.putAll(from) }

    private inline fun <R> mutateAndNotify(block: (KMutableMap<K, V>) -> R): R {
        val map = live.value.toMutableMap()
        val result = block(map)
        live.value = map.toIMap()
        return result
    }
}