package org.movies.android.testesodexo.data.executor

import io.reactivex.Scheduler

/**
 * Abstração de thread criada para alterar o contexto de execução de qualquer thread para qualquer outro thread.
 * Útil para encapsular um thread de interface do usuário, por exemplo, uma vez que algum trabalho será feito em background, um
 * A implementação desta interface mudará o contexto e atualizará a interface do usuário.
 */
interface PostExecutionThread {
    val scheduler: Scheduler
}