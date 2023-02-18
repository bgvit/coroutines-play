package bgvit.github.com.br.plugins

import bgvit.github.com.br.service.CoroutinesExampleService
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import kotlinx.coroutines.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {

    val coroutinesService by inject<CoroutinesExampleService>()

    routing {

        get("allValues") {
            coroutineScope {
                val mpayValues = async { coroutinesService.listValuesFromMovilePay() }
                val ifoodValues = async { coroutinesService.listValuesFromiFood() }
                call.respond(HttpStatusCode.Accepted, coroutinesService.unionValues(ifoodValues.await(), mpayValues.await()))
            }
        }

        get("error") {
            val handler = CoroutineExceptionHandler { _, exception ->
                println("could not handle because of ${exception.message}")
                println("CoroutineExceptionHandler got $exception")
            }

            supervisorScope {
                launch(handler) { coroutinesService.process() }
            }

            call.respond(HttpStatusCode.Accepted, "FOI")
        }
    }
}
