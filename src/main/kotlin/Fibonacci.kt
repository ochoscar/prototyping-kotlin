package com.ochoscar

fun main() {
    tailrec fun fib(a: Int, b: Int, i: Int): Int =
        if(i == 1) b
        else fib(b, a + b, i - 1)
    print("number: ")
    val num = readln().toInt()
    print("Fibonacci(${num}): ${fib(0, 1, num)}")
}

