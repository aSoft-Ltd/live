package live.internal

import kotlin.collections.Collection as KCollection
import kotlin.collections.Map as KMap
import kotlin.collections.MutableMap as KMutableMap
import kollections.MapEntry
import kollections.Map
import kollections.MutableMap
import kollections.toIMutableList
import kollections.iMapOf
import kollections.toIMap
import kollections.internal.AbstractCollection
import kollections.toIMutableMap
import kollections.toIMutableSet
import live.MutableLive
import live.MutableLiveMap

@PublishedApi
internal class MutableLiveMapWrapper<K, V>(
    private val live: MutableLive<Map<K, V>>
) : AbstractLiveCollection<MapEntry<K, V>>(live), MutableLiveMap<K, V>, MutableLive<Map<K, V>> by live {
    override fun containsAll(elements: KCollection<MapEntry<K, V>>): Boolean = live.value.containsAll(elements)

    override val size get() = live.value.size

    override val keys get() = live.value.keys.toIMutableSet()

    override val values get() = live.value.values.toIMutableList()

    override val pairs get() = live.value.pairs

    override val entries get() = live.value.toMutableMap().entries

    override fun get(key: K): V? = live.value[key]

    override fun getValue(key: K): V = live.value.getValue(key)

    override fun iterator(): Iterator<MapEntry<K, V>> = live.value.iterator()

    override fun clear() {
        live.value = iMapOf()
    }

    override fun remove(key: K): V? = update { it.remove(key) }

    override fun put(key: K, value: V): V? = update { it.put(key, value) }

    override fun putAll(from: KMap<out K, V>) = update { it.putAll(from) }

    override fun set(key: K, value: V) {
        put(key, value)
    }

    override fun <R> update(block: (MutableMap<K, V>) -> R): R {
        val map = live.value.toIMutableMap()
        val result = block(map)
        live.value = map.toIMap()
        return result
    }
}