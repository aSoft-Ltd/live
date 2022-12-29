package live.internal

import kotlin.collections.Collection as KCollection
import kotlin.collections.Map as KMap
import kotlin.collections.MutableMap as KMutableMap
import kollections.MapEntry
import kollections.Map
import kollections.toIMutableList
import kollections.iMapOf
import kollections.toIMap
import kollections.internal.AbstractCollection
import live.MutableLive
import live.MutableLiveMap

internal class MutableLiveMapWrapper<K, V>(
    private val live: MutableLive<Map<K, V>>
) : AbstractCollection<MapEntry<K, V>>(), MutableLiveMap<K, V>, MutableLive<Map<K, V>> by live {
    override fun containsAll(elements: KCollection<MapEntry<K, V>>): Boolean = live.value.containsAll(elements)

    override val size get() = live.value.size
    val keys get() = live.value.keys
    val values get() = live.value.values.toIMutableList()
    val pairs get() = live.value.pairs

    fun get(key: K): V? = live.value[key]

    fun getValue(key: K): V = live.value.getValue(key)

    val entries get() = live.value.toMutableMap().entries

    override fun isEmpty(): Boolean = live.value.isEmpty()

    override fun contains(element: MapEntry<K, V>): Boolean = live.value.contains(element)

    override fun iterator(): Iterator<MapEntry<K, V>> = live.value.iterator()

    fun clear() {
        live.value = iMapOf()
    }

    override fun remove(key: K): V? = mutateAndNotify { it.remove(key) }

    fun put(key: K, value: V): V? = mutateAndNotify { it.put(key, value) }

    fun putAll(from: KMap<out K, V>) = mutateAndNotify { it.putAll(from) }

    override fun set(key: K, value: V) = put(key, value)

    private inline fun <R> mutateAndNotify(block: (KMutableMap<K, V>) -> R): R {
        val map = live.value.toMutableMap()
        val result = block(map)
        live.value = map.toIMap()
        return result
    }
}