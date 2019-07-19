package coroutines

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {

    // runBlocking blocks the current thread until it's coroutine
    // children complete (not a suspend function)
    runBlocking {

        // coroutineScope does not block the current thread until the
        // children complete (this is a suspendable function)
        coroutineScope {
            launch {
                delay(500L)
                println("runBlocking first")
            }

            launch {
                delay(200L)
                println("runBlocking second")
            }
        }

        println("runBlocking end")
    }
}
