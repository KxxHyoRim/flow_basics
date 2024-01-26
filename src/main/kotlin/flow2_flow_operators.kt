import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking


fun flowSomething(): Flow<Int> = flow {
    for (i in 0..20) {
        emit(i)
        delay(10L)
    }
}

fun map() = runBlocking {
    flowSomething()
        .map { "$it $it" }
        .collect { print("$it -> ") }
}

fun filter() = runBlocking {
    flowSomething()
        .filter { (it % 2) == 0 }
        .collect { print("$it -> ") }
}

fun filterNot() = runBlocking {
    flowSomething()
        .filterNot { (it % 2) == 0 }
        .collect { print("$it -> ") }
}


fun transform() = runBlocking {
    flowSomething()
        .transform {
            emit(it)
            emit(it * 2)
        }.collect { print("$it -> ") }
}


fun take() = runBlocking {
    flowSomething()
        .take(5)
        .collect { print("$it -> ") }
}

fun takeWhile() = runBlocking {
    flowSomething()
        .takeWhile { it == 3 }
        .collect { print("$it -> ") }
}

fun drop() = runBlocking {
    flowSomething()
        .drop(21) // 0, 1, 2 이렇게 3개 버림
        .collect { print("$it -> ") }
}

fun dropWhile() = runBlocking {
    flowSomething()
        .dropWhile { it % 2 == 0 }
        .collect { print("$it -> ") }
}

fun toList() = runBlocking {
    flowSomething()
        .dropWhile { it % 2 == 0 }
        .collect { print("$it -> ") }
}

fun toSet() = runBlocking {
    flowSomething()
        .dropWhile { it % 2 == 0 }
        .collect { print("$it -> ") }
}

fun first() = runBlocking {
    println(flowSomething().first())
}

fun single() = runBlocking {
    println(flowOf("singleValue").single())
//    println(flowOf("multipleValue", "occurException").single())
}

fun reduce() = runBlocking {
    // 0, 1, .. 20 차례로 더하기
    println(
        flowSomething().reduce { accumulator, value ->
            accumulator + value // sum of 0 to 20
        }
    )
}

fun fold() = runBlocking {
    // 100, 0, 1, .. 20 차례로 더하기
    println(
        flowSomething().fold(100) { accumulator, value ->
            accumulator + value // sum of 0 to 20, plus 100
        }
    )
}

fun count() = runBlocking {
    // 홀수 몇개인지 세기
    println(
        flowSomething().count { it % 2 == 0 }
    )
}

fun main(): Unit = runBlocking {
//    map()
//    filter()
//    filterNot()
//    transform()
//    take()
//    takeWhile()
//    drop()
//    dropWhile()
//    first()
//    single()
//    reduce()
//    fold()
    count()
    delay(1000)
}