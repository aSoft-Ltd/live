package live

import koncurrent.Executor
import useLive

inline fun <S> Live<S>.watchAsState(executor: Executor? = null) = useLive(this, executor)