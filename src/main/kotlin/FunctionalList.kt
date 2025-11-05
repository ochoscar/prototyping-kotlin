package com.ochoscar

sealed class MyList<out A> {
    object Nil: MyList<Nothing>()

    data class Cons<out A>(val head: A , val tail: MyList<A>): MyList<A>()

    companion object {
        fun <A> of(vararg aa: A): MyList<A> {
            val tail = (aa as Array).sliceArray(1 until aa.size)
            return if (aa.isEmpty()) Nil else Cons(aa[0], of(*tail))
        }

        fun <A> tail(xs: MyList<A>): MyList<A> {
            return when(xs) {
                is Nil -> Nil
                is Cons -> xs.tail
            }
        }
    }

}


