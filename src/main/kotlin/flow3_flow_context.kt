import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

fun simple(): Flow<Int> = flow {
    printWithThread("Started simple flow")
    for (i in 1..3) {
        emit(i)
    }
}

fun wrongSimple(): Flow<Int> = flow {
    // The WRONG way to change context for CPU-consuming code in flow builder
    withContext(Dispatchers.Default) {
        for (i in 1..3) {
            Thread.sleep(100) // pretend we are computing it in CPU-consuming way
            emit(i) // emit next value
        }
    }
}

fun rightSimple(): Flow<Int> = flow {
    for (i in 1..3) {
        Thread.sleep(100) // pretend we are computing it in CPU-consuming way
        printWithThread("Emitting $i")
        emit(i) // emit next value
    }
}.flowOn(Dispatchers.Default) // RIGHT way to change context for CPU-consuming code in flow builder

fun myExample(): Flow<Int> = flow {
    withContext(Dispatchers.Default) { printWithThread("Emit은 안해요") }
    for (i in 1..3) {
        Thread.sleep(100) // pretend we are computing it in CPU-consuming way
        printWithThread("Emitting $i")
        emit(i) // emit next value
    }
}.flowOn(Dispatchers.IO) // RIGHT way to change context for CPU-consuming code in flow builder

fun main() = runBlocking<Unit> {
//    simple().collect { value -> printWithThread("Collected $value") }
//    wrongSimple().collect { value -> printWithThread("Collected $value") }
//    rightSimple().collect { value -> printWithThread("Collected $value") }
    withContext(Dispatchers.Default) {
        myExample().collect { value -> printWithThread("Collected $value") }
    }
}

fun printWithThread(str: Any?) {
    println("[${Thread.currentThread().name}] $str")
}