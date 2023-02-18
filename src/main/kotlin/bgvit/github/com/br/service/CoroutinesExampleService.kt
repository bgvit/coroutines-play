package bgvit.github.com.br.service

import bgvit.github.com.br.enums.IfoodValues
import bgvit.github.com.br.enums.MovilePayValues
import kotlinx.coroutines.*

class CoroutinesExampleService {

    suspend fun listValuesFromMovilePay() = MovilePayValues.values()

    suspend fun listValuesFromiFood() = IfoodValues.values()

    suspend fun unionValues(ifood : Array<IfoodValues>, mpay : Array<MovilePayValues>) : List<String> {
        return coroutineScope {
            val ifood = async { ifood.map { value -> value.toString() } }
            val mpay = async { mpay.map { value -> value.toString() } }
             ifood.await().plus(mpay.await())
        }
    }

    suspend fun process(){
        process1()
        process2()
        println("DEU BOM")
    }

    suspend fun processWithExceptionHandler(){
        coroutineScope() {
            launch(Dispatchers.IO) { process1() }
            launch(Dispatchers.Unconfined) { process1() }
            println("DEU BOM")
        }
    }

    private suspend fun process1() {
        try {
            Thread.sleep(10000)
        } catch (t: Throwable) {
            println("JOIA, DEU RUIM!!!!")
        }
    }

    private suspend fun process2(){
        try {
            throw IllegalArgumentException()
        } catch (t: Throwable) {
            println("MEU ERRO NAO QUEBROU AS OUTRAS COROUTINES")
        }

    }
}