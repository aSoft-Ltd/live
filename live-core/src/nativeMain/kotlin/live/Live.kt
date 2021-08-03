package live

import kotlinx.atomic.collections.mutableAtomicListOf

actual class Live<T> actual constructor(v: T) {
    actual var value: T = v
        set(value) {
            field = value
            for (watcher in watchers) watcher.callable(value)
        }

    private val watchers = mutableAtomicListOf<Watcher<T>>()

    actual fun watch(callable: (T) -> Unit): Watcher<T> = watch(watchers, callable)

    actual fun stop(watcher: Watcher<T>) = watchers.remove(watcher)

    actual fun stopAll() = watchers.clear()
}