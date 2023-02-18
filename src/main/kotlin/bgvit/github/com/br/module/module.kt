package bgvit.github.com.br.module

import bgvit.github.com.br.service.CoroutinesExampleService
import org.koin.dsl.module

val myModule = module {
    single { CoroutinesExampleService() }
}