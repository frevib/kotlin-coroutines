package coroutines

import kotlinx.coroutines.*

fun main() {

    runBlocking {

        launch(Dispatchers.Default + CoroutineName("X")) {
            // the code running behind makeCoffee (grindBeans(), extractCofee()
            // and addMilk() is running synchronously.
            // So everything inside the the coroutine builder, in this case 'launch()'
            // is running synchronously
            makeCoffee("X")
        }

        launch(Dispatchers.Default + CoroutineName("Y")) {
            makeCoffee("Y")
        }
    }
}

suspend fun makeCoffee(name: String) {
    grindBeans(name)
    extractCoffee(name)
    addMilk(name)
}

suspend fun grindBeans(name: String) {
    println("grinding beans $name")
    delay(2000)
    println("grinding beans complete $name...")
}

suspend fun extractCoffee(name: String) {
    println("extracting coffee $name")
    delay(1000)
    println("extract coffee complete $name....")
}

suspend fun addMilk(name: String) {
    println("add milk $name")
    delay(500)
    println("done adding milk $name...")
}
