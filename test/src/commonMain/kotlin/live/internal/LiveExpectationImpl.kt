package live.internal

import expect.CollectionExpectation
import expect.expect
import live.Live
import live.LiveExpectation
import kotlin.math.sign
import kotlin.test.assertEquals

class LiveExpectationImpl<out S>(
    private val live: Live<S>
) : LiveExpectation<S>, CollectionExpectation<S> by expect(collection = live.history.toMutableList()) {

    override val value: List<S> get() = (live.history.toMutableList() + live.value).drop(1)

    override fun toBeIn(state: @UnsafeVariance S) {
        assertEquals(
            state, value.lastOrNull(),
            """
            
        Expected ViewModel State : $state
        Actual ViewModel State   : ${value.lastOrNull()}
        ==================================
        
        """.trimIndent()
        )
    }

    private fun StringBuilder.appendStates(states: Iterable<S>) = states.forEachIndexed { index, s ->
        appendLine("\t${index + 1}. $s")
    }

    private fun expectActualMessage(expecteds: List<S>, actuals: List<S>) = buildString {
        appendLine("State phases didn't match")
        appendLine()
        val diff = expecteds.size - actuals.size
        val zipped = when {
            diff > 0 -> expecteds.zip(actuals + List(diff * diff.sign) { null })
            diff < 0 -> (expecteds + List(diff * diff.sign) { null }).zip(actuals)
            else -> expecteds.zip(actuals)
        }
        for (zx in zipped.indices) {
            val (ex, ax) = zipped[zx]
            appendLine("[Phase ${zx + 1}]" + if (ex != ax) " -> MISMATCH HERE" else "")
            appendLine("\tExpected: ${ex?.toString() ?: "NO EXPECTED STATE"}")
            appendLine("\tActual  : ${ax?.toString() ?: "NO ACTUAL STATE"}")
            appendLine()
        }
    }

    override fun toHaveGoneThrough(vararg states: @UnsafeVariance S): List<S> {
        assertEquals(
            states.toList().toString(), value.toString(),
            expectActualMessage(states.toList(), value)
        )
        return value
    }
}