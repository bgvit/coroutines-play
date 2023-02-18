package bgvit.github.com.br

import bgvit.github.com.br.module.myModule
import io.ktor.server.application.*
import bgvit.github.com.br.plugins.*
import io.github.smiley4.ktorswaggerui.SwaggerUI
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
import org.slf4j.Logger

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {

    install(Koin) {
        slf4jLogger()
        modules(myModule)
    }

    configureSerialization()
    configureRouting()
    configureSwagger()
}
