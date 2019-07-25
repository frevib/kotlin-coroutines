package coroutines

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val channel = Channel<Int>()
        launch {
            // Every second an item is put in the channel. Right after the item
            // is sent to the channel, this function suspends for 1 second.
            for (x in 1..5) {
                channel.send(x)
                delay(1000)
            }
        }

        launch {
            receiveFromChannel(channel)
        }
    }
}


suspend fun receiveFromChannel(channel: ReceiveChannel<Int>) {
    // When there are items in the channel, the code inside the loop will run.
    // When there are no items in the channel, this function suspends.
    for (element in channel) {
        println("Received element from channel: $element")
    }
}
