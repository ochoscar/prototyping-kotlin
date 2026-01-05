package com.ochoscar

sealed class MyList<out A> {
    object Nil: MyList<Nothing>()

    data class Cons<out A>(val head: A , val tail: MyList<A>): MyList<A>()

    companion object {
        fun <A> of(vararg aa: A): MyList<A> {
            val tail = aa.sliceArray(1 until aa.size)
            return if (aa.isEmpty()) Nil else Cons(aa[0], of(*tail))
        }

        fun <A> tail(xs: MyList<A>): MyList<A> {
            return when(xs) {
                is Nil -> Nil
                is Cons -> xs.tail
            }
        }

        fun <A> setHead(xs: MyList<A>, x: A): MyList<A> {
            return when(xs) {
                is Nil -> Cons(x, Nil)
                is Cons -> Cons(x, xs)
            }
        }

        tailrec fun <A> drop(l: MyList<A>, n: Int): MyList<A> {
            return if(n == 0) l
            else drop(tail(l), n - 1)
        }

        tailrec fun <A> dropWhile(l: MyList<A>, f: (A) -> Boolean): MyList<A> {
            return when(l) {
                is Nil -> Nil
                is Cons -> if(f(l.head)) dropWhile(tail(l), f) else l
            }
        }

        fun <A> init(l: MyList<A>): MyList<A> {
            return when(l) {
                is Nil -> Nil
                is Cons -> if(l.tail != Nil) Cons(l.head, init(l.tail)) else l
            }
        }

        fun <A, B> foldRight(l: MyList<A>, z: B, f: (A, B) -> B): B {
            return when(l) {
                is Nil -> z
                is Cons -> f(l.head, foldRight(l.tail, z, f))
            }
        }

        fun <A, B> foldRightWithStop(l: MyList<A>, z: B, f: (A, B) -> B, sF: (A) -> Boolean, sV: B): B {
            return when {
                l is Nil -> z
                sF((l as Cons).head) -> sV
                else -> f((l).head, foldRightWithStop(l.tail, z, f, sF, sV))
            }
        }

        fun <A> empty(): MyList<A> = Nil

        fun <A> length(l: MyList<A>): Int {
            return foldRight(l, 0) { _, acc -> acc + 1 }
        }

        tailrec fun <A, B> foldLeft(xs: MyList<A>, z: B, f: (B, A) -> B): B {
            return when(xs) {
                is Nil -> z
                is Cons -> foldLeft(xs.tail, f(z, xs.head), f)
            }
        }

        // Reverse items
        fun <A> reverseRecursive(xs: MyList<A>): MyList<A> {
            fun reverse(list: MyList<A>): MyList<A> {
                return when(list) {
                    is Nil -> Nil
                    is Cons -> addAtLast(reverse(list.tail), list.head)
                }
            }
            return reverse(xs)
        }

        fun <A> reverseWithFold(xs: MyList<A>): MyList<A> {
            return foldRight(xs, Nil) { item: A, acc: MyList<A> -> Cons(item, acc) }
        }

        fun <A> addAtLast(xs: MyList<A>, item: A): MyList<A> {
            return when(xs) {
                is Nil -> Cons(item, Nil)
                is Cons -> Cons(xs.head, addAtLast(xs.tail, item))
            }
        }

        // Implemented foldLeft in terms of foldRight
        fun <A, B> foldLeft2(xs: MyList<A>, z: B, f: (A, B) -> B): B =
            foldRight(xs, { b: B -> b }) { a, g ->
                { b -> g(f(b as A, a as B)) }
            }(z)

        fun <A> printList(xs: MyList<A>): Unit {
            when(xs) {
                is Nil -> println("")
                is Cons -> {
                    print(" ${xs.head}")
                    printList(xs.tail)
                }
            }
        }

        // Append with fold
        fun <A> appendWithFold(xs: MyList<A>, lastItem: A): MyList<A> {
            return foldLeft(xs, Cons(lastItem, Nil)) { acc: MyList<A>, item: A ->
                Cons(item, acc)
            }
        }

        // Concatenate list
        fun <A> concatenate(xa: MyList<A>, xb: MyList<A>): MyList<A> {
            fun createList(xb: MyList<A>, xConcatenated: MyList<A> ): MyList<A> {
                return when(xb) {
                    is Nil -> xConcatenated
                    is Cons -> Cons(xb.head, createList(xb.tail, xConcatenated))
                }
            }
            return createList(xb, xa)
        }

        // Add 1 to an integer list
        fun add1(xs: MyList<Int>): MyList<Int> {
            fun go(items: MyList<Int>): MyList<Int> {
                return when (items) {
                    is Nil -> Nil
                    is Cons -> Cons(items.head + 1, go(items.tail))
                }
            }
            return go(xs)
        }

    }

}


