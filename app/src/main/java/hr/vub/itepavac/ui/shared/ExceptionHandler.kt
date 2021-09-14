package hr.vub.itepavac.ui.shared

import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext

class ExceptionHandler @Inject constructor(
    private val logger: Logger
) : AbstractCoroutineContextElement(CoroutineExceptionHandler), CoroutineExceptionHandler {

    override fun handleException(context: CoroutineContext, exception: Throwable) {
        logger.log("Coroutine exception caught", exception)
    }
}
