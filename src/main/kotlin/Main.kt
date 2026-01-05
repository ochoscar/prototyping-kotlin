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

    val product4 = MyList.foldLeft2(list, 1, { a, b -> a * b})
    println("product4: $product3")

    val reversed1 = MyList.reverseRecursive(list2)
    MyList.printList(reversed1)

    val reversed2 = MyList.reverseRecursive(list2)
    MyList.printList(reversed2)

    val listAppend = MyList.of(1, 2, 3, 4, 5, 0, 7, 8, 9)
    val listAppended = MyList.appendWithFold(listAppend, 5)
    MyList.printList(listAppended)

    val concatenatedList = MyList.concatenate(list2, list2)
    MyList.printList(concatenatedList)

    val added1 = MyList.add1(list)
    MyList.printList(added1)

    val mapTest = MyList.map<Int, Int>(list,{item -> item * 2} )
    val mapTest2 = MyList.map<Int, Int>(list, { it * 2 } )
    MyList.printList(mapTest2)
}

fun greetings(name: String): String {
    return "Hi, $name!"
}
