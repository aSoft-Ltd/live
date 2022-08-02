package live

inline fun <
        reified S1
        > LiveExpectation<*>.toHaveGoneThrough1() = DestructibleList<S1, Any?, Any?, Any?, Any?, Any?>(
    toHaveGoneThrough(S1::class)
)

inline fun <
        reified S1,
        reified S2
        > LiveExpectation<*>.toHaveGoneThrough2() = DestructibleList<S1, S2, Any?, Any?, Any?, Any?>(
    toHaveGoneThrough(S1::class, S2::class)
)

inline fun <
        reified S1,
        reified S2,
        reified S3
        > LiveExpectation<*>.toHaveGoneThrough3() = DestructibleList<S1, S2, S3, Any?, Any?, Any?>(
    toHaveGoneThrough(S1::class, S2::class, S3::class)
)

inline fun <
        reified S1,
        reified S2,
        reified S3,
        reified S4
        > LiveExpectation<*>.toHaveGoneThrough4() = DestructibleList<S1, S2, S3, S4, Any?, Any?>(
    toHaveGoneThrough(S1::class, S2::class, S3::class, S4::class)
)

inline fun <
        reified S1,
        reified S2,
        reified S3,
        reified S4,
        reified S5
        > LiveExpectation<*>.toHaveGoneThrough5() = DestructibleList<S1, S2, S3, S4, S5, Any?>(
    toHaveGoneThrough(S1::class, S2::class, S3::class, S4::class, S5::class)
)

inline fun <
        reified S1,
        reified S2,
        reified S3,
        reified S4,
        reified S5,
        reified S6
        > LiveExpectation<*>.toHaveGoneThrough6() = DestructibleList<S1, S2, S3, S4, S5, S6>(
    toHaveGoneThrough(S1::class, S2::class, S3::class, S4::class, S5::class, S6::class)
)