package live

import koncurrent.Executor
import koncurrent.SynchronousExecutor
import useLiveWithExecutor
import useNullableLiveWithExecutor

inline fun <S> Live<S>.watchAsState(executor: Executor = SynchronousExecutor) = useLiveWithExecutor(this, executor)

inline fun <S> Live<S>?.watchAsState(executor: Executor = SynchronousExecutor) = useNullableLiveWithExecutor(this, executor)