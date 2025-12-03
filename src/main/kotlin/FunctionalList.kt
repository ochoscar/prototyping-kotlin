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
                else -> f((l as Cons).head, foldRightWithStop(l.tail, z, f, sF, sV))
            }
        }

        fun <A> empty(): MyList<A> = Nil

        fun <A> lenght(l: MyList<A>): Int {
            return foldRight(l, 0) { _, acc -> acc + 1}
        }
    }

}


