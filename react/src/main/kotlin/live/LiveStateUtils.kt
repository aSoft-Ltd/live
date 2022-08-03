package live

import koncurrent.Executor
import koncurrent.SynchronousExecutor
import useLiveWithExecutor

inline fun <S> Live<S>.watchAsState(executor: Executor = SynchronousExecutor) = useLiveWithExecutor(this, executor)