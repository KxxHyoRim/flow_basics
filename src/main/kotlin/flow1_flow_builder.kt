import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun simpleFlowBuilder(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(1000)
        emit(i)
    }
}

fun simpleFlowOfBuilder(): Flow<String> = flowOf("A", "B", "C")

fun simpleAsFlowBuilder(): Flow<Double> {
    return listOf(1.1, 2.2, 3.3).asFlow()
}

fun main() = runBlocking {
    // Using flow{}
    println("Using flow{} builder function:")
    simpleFlowBuilder().collect { value ->
        println("Collected value: $value")
    }

    // Using flowOf{}
    println("\nUsing flowOf{} builder function:")
    simpleFlowOfBuilder().collect { value ->
        println("Collected value: $value")
    }

    // Using asFlow{}
    println("\nUsing asFlow{} builder function:")
    simpleAsFlowBuilder().collect { value ->
        println("Collected value: $value")
    }
}
