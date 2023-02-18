package bgvit.github.com.br.plugins

import io.github.smiley4.ktorswaggerui.SwaggerUI
import io.ktor.server.application.*

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.configureSwagger() {

    install(SwaggerUI) {
        swagger {
            swaggerUrl = "swagger-ui"
            forwardRoot = true
        }
        info {
            title = "Example API"
            version = "latest"
            description = "Example API for testing and demonstration purposes."
        }
        server {
            url = "http://localhost:8080"
            description = "Development Server"
        }
    }
}