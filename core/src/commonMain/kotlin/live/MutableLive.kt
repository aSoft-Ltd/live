package live

import kotlin.jvm.JvmSynthetic

expect interface MutableLive<S> : Live<S> {
    override val history: MutableList<S>

    override var value: S

    fun undo()

    fun redo()

    /**
     * Transforms this live to another live
     */
    @JvmSynthetic
    override fun <R> map(transformer: (S) -> R): MutableLive<R>

    /**
     * Notify the watchers without changing the underlying value
     */
    fun dispatch(value: S)

    fun dispatch()
}