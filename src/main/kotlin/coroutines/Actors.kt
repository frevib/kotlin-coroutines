package coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.actor


sealed class ActorMessage
class Calculate(val f: (Int) -> Int): ActorMessage()
class GetResponse(val response: CompletableDeferred<Int>): ActorMessage()


fun main() {
    runBlocking {
        val actor = routerActor()

        withContext(Dispatchers.Default) {
            actor.send(Calculate { it + 1 * 2 })
        }

        val response = CompletableDeferred<Int>()
        actor.send(GetResponse(response))
        println("Counter: ${response.await()}")

        actor.close()
    }

}


fun CoroutineScope.routerActor() = actor<ActorMessage> {
    var total = 0
    for (msg in channel) {
        when (msg) {
            is Calculate -> total = msg.f(total)
            is GetResponse -> msg.response.complete(total)
        }
    }
}
