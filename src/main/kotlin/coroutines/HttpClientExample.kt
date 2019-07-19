package coroutines

import kotlinx.coroutines.*
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


fun main() {

    runBlocking {
        println("start!")

        (0..10).forEach {
                launch((Dispatchers.Default)) {
                    val response = getRequest(it)
                    println("[${Thread.currentThread().name}] - $response")
                }
                println("done...")
            }

        println("end!")
    }
}


suspend fun getRequest(number: Int) =
    suspendCoroutine<String> { continuation ->
        val httpClient = HttpClients.createDefault()
        val httpGet = HttpGet("http://ec2-13-53-123-226.eu-north-1.compute.amazonaws.com/1337.txt")
        val response = httpClient.execute(httpGet)
        continuation.resume(EntityUtils.toString(response.entity) + " -- " + number.toString())
    }


