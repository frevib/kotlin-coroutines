package coroutines

fun main() {
    println(plusFour(timesTwo(4)))

    val combined = ::timesTwo andThen :: plusFour
    println(combined(4))
}

private fun timesTwo(x: Int): Int = x * 2

private fun plusFour(x: Int) = x + 4


infix fun <A> ((A) -> A).andThen(f: (A) -> A): (A) -> A = { x -> f(this(x)) }
