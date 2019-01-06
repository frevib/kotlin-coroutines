package coroutines

import kotlinx.coroutines.*

fun main() {

    val result = runBlocking { joinString() }
}

suspend fun joinString(): String {

    val result = mutableListOf<String>()

    val job = GlobalScope.launch {
        delay(2000L)
        result.add("coroutine")
    }

    result.add("hello, ")
    job.join()

    return result.joinToString("")
}
