package com.ochoscar

val <T> List<T>.tail: List<T>
    get() = drop(1)

val <T> List<T>.head: T
    get() = first()

fun <A> isSorted(aa: List<A>, order: (A, A) -> Boolean): Boolean {
    if( aa.size < 2 ) return true
    return aa.drop(1).fold(
        Pair(aa.first(), true),
        fun(acc: Pair<A, Boolean>, b: A): Pair<A, Boolean> {
            return Pair(b, acc.second && order(acc.first, b))
        }).second
}

fun <A> isSorted2(aa: List<A>, order: (A, A) -> Boolean): Boolean {
    return aa.zipWithNext().all { (a, b) -> order(a, b) }
}

fun <A> isSorted3(aa: List<A>, order: (A, A) -> Boolean): Boolean {
    if( aa.size < 2 ) return true
    return aa.tail.fold(
        Pair(aa.head, true),
        fun(acc: Pair<A, Boolean>, b: A): Pair<A, Boolean> {
            return Pair(b, acc.second && order(acc.first, b))
        }).second
}

fun main() {
    val data1 = listOf<String>("abc3", "abc2", "abc4", "abc5", "abc1")
    val data2 = listOf<String>("abc1", "abc2", "abc3", "abc4", "abc5")
    fun <A: Comparable<A>> order(item1: A, item2: A): Boolean =  item1 < item2
    val sorted1 = isSorted3<String>(data1, ::order)
    val sorted2 = isSorted3<String>(data2, ::order)
    println("Data 1 is sorted: $sorted1")
    println("Data 2 is sorted: $sorted2")
}