package coroutines

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {

    val channel1 = Channel<Int>(10)
    val channel2 = Channel<Int>(10)

    launch {
        produceNumbers(channel1)
    }

    launch {
        squareNumbers(channel1, channel2)
    }

    launch {
        for (i in 1..5) {
            println(channel2.receive())
        }
    }

    println("Done!")
}


suspend fun produceNumbers(channel: SendChannel<Int>) {
    for (n in 0 until 10) {
        channel.send(n)
    }
    channel.close()
}


suspend fun squareNumbers(receiveChannel: ReceiveChannel<Int>, sendChannel: SendChannel<Int>) {
    for (i in receiveChannel) {
        sendChannel.send(i * 3)
    }
}
