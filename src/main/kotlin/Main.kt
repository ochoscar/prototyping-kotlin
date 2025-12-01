package com.ochoscar

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    println("Hello Kotlin!")

    val list = MyList.of(1, 2, 3, 4, 5)
    val sum = MyList.foldRigth(list, 0, { a, b -> a + b})
    println("sum: $sum")
    val product = MyList.foldRigth(list, 1, { a, b -> a * b})
    println("product: $product")
}

fun greetings(name: String): String {
    return "Hi, $name!"
}
