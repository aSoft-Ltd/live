package live

import kotlin.jvm.JvmInline

@JvmInline
value class DestructibleList<S1, S2, S3, S4, S5, S6>(private val value: List<*>) : List<Any?> by value {
    operator fun component1(): S1 = value[0] as S1
    operator fun component2(): S2 = value[1] as S2
    operator fun component3(): S3 = value[2] as S3
    operator fun component4(): S4 = value[3] as S4
    operator fun component5(): S5 = value[4] as S5
    operator fun component6(): S6 = value[5] as S6
}