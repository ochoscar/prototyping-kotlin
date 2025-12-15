package com.ochoscar

fun main() {
    println("Hello Kotlin!")

    val list = MyList.of(1, 2, 3, 4, 5)
    val sum = MyList.foldRight(list, 0, { a, b -> a + b})
    println("sum: $sum")
    val product = MyList.foldRight(list, 1, { a, b -> a * b})
    println("product: $product")

    val list2 = MyList.of(1, 2, 3, 4, 5, 0, 7, 8, 9)
    val product2 = MyList.foldRightWithStop(list2, 1, { a, b -> a * b}, { a -> a ==0 }, 0)
    println("product2: $product2")

    println("List size: ${MyList.length(list2)}")

    val product3 = MyList.foldLeft(list, 1, { a, b -> a * b})
    println("product3: $product3")

    val reversed = MyList.reverseRecursive(list2)
    MyList.printList(reversed)
}

fun greetings(name: String): String {
    return "Hi, $name!"
}
