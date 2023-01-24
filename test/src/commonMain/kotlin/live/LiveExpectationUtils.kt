package live

inline fun <
        reified S1 : Any
        > LiveExpectation<Any?>.toHaveGoneThrough1() = DestructibleList<S1, Any?, Any?, Any?, Any?, Any?>(
    toHaveGoneThrough(S1::class)
)

inline fun <
        reified S1 : Any,
        reified S2 : Any
        > LiveExpectation<Any?>.toHaveGoneThrough2() = DestructibleList<S1, S2, Any?, Any?, Any?, Any?>(
    toHaveGoneThrough(S1::class, S2::class)
)

inline fun <
        reified S1 : Any,
        reified S2 : Any,
        reified S3 : Any
        > LiveExpectation<Any?>.toHaveGoneThrough3() = DestructibleList<S1, S2, S3, Any?, Any?, Any?>(
    toHaveGoneThrough(S1::class, S2::class, S3::class)
)

inline fun <
        reified S1 : Any,
        reified S2 : Any,
        reified S3 : Any,
        reified S4 : Any
        > LiveExpectation<Any?>.toHaveGoneThrough4() = DestructibleList<S1, S2, S3, S4, Any?, Any?>(
    toHaveGoneThrough(S1::class, S2::class, S3::class, S4::class)
)

inline fun <
        reified S1 : Any,
        reified S2 : Any,
        reified S3 : Any,
        reified S4 : Any,
        reified S5 : Any
        > LiveExpectation<Any?>.toHaveGoneThrough5() = DestructibleList<S1, S2, S3, S4, S5, Any?>(
    toHaveGoneThrough(S1::class, S2::class, S3::class, S4::class, S5::class)
)

inline fun <
        reified S1 : Any,
        reified S2 : Any,
        reified S3 : Any,
        reified S4 : Any,
        reified S5 : Any,
        reified S6 : Any
        > LiveExpectation<Any?>.toHaveGoneThrough6() = DestructibleList<S1, S2, S3, S4, S5, S6>(
    toHaveGoneThrough(S1::class, S2::class, S3::class, S4::class, S5::class, S6::class)
)