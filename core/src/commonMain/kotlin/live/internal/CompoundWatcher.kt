package live.internal

import live.Watcher

internal class CompoundWatcher(val watchers: Collection<Watcher>) : Watcher {
    override fun stop() {
        watchers.forEach { it.stop() }
    }
}