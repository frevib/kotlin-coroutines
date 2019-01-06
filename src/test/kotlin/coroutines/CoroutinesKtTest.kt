package coroutines

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test


internal class CoroutinesTest {

    @Test
    fun test_joinToString() {

        val result = runBlocking { joinString() }

        assertEquals(result, "hello, coroutine")

    }
}

