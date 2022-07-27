package live

import koncurrent.Executor
import live.internal.AbstractLive

class MockLive<S>(initial: S) : AbstractLive<S>(), MutableLive<S> {

    override var value: S = initial
        get() = TODO()
        set(value) {
            field = value
        }

    override fun watchRaw(callback: ((state: S) -> Unit)?, mode: WatchMode?, executor: Executor?): Watcher {
        TODO("Not yet implemented")
    }

    override fun stopAll() {
        TODO("Not yet implemented")
    }
}