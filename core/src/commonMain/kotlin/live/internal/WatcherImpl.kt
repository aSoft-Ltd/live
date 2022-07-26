package live.internal

import koncurrent.Executor
import live.Watcher
import kotlin.js.JsExport
import kotlin.jvm.JvmSynthetic

internal class WatcherImpl<in V> internal constructor(
    private val container: MutableList<WatcherImpl<V>>,
    private val executor: Executor,
    private val callable: (V) -> Unit
) : Watcher {
    fun execute(value: V) = executor.execute { callable(value) }
    override fun stop() {
        container.remove(this)
    }
}