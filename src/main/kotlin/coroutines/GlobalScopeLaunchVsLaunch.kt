package coroutines


import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() {

    runBlocking {
        // this coroutine runs in the 'runBlocking' scope.
        // runBlocking will not complete until it's inner coroutines (withoutGlobal()) complete
        launch {
            withoutGlobal()
        }
        println("end of withoutGlobal")
    }


    runBlocking {
        // GlobalScope.launch runs as a top-level coroutine, it's not in the scope
        // of another coroutine. So it will not wait until the a higher level coroutine
        // completes, as there is no higher level coroutine.
        GlobalScope.launch {
            withGlobal()
        }
        println("end of withGlobal")
    }

}

val withGlobal = suspend {
    delay(1000)

    // this is not printed as GlobalScope.launch runs as a top-level coroutine, and
    // therefore will not wait until a higher level level coroutine finishes.
    println("with Global")

}

var withoutGlobal = suspend {
    delay(1500)

    // this will be printed because launch runs inside the scope of the 'runBlocking'
    // that is wrapping this function. runBlocking will wait untill all it's coroutines inside
    // complete
    println("without Global")
}
