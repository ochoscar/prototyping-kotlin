package com.ochoscar

sealed class Stream<out A>

object Empty: Stream<Nothing>()

data class Cons<out A> (
    val head: () -> A,
    val tail: () -> Stream<A>
): Stream<A>()

fun <A> Stream<A>.empty(): Stream<A> = Empty

fun <A> cons(hd: () -> A, tl: () -> Stream<A> ): Stream<A> {
    val head: A by lazy(hd)
    val tail: Stream<A> by lazy(tl)
    return Cons({ head }, { tail })
}

fun <A> Stream<A>.of(vararg xs: A): Stream<A> {
    return when {
        xs.isEmpty() -> empty()
        else -> cons({ xs[0] }, { of(*xs.sliceArray(1 until xs.size)) })
    }
}

fun <A> Stream<A>.headOption(): Option<A> {
    return when(this) {
        is Empty -> None
        is Cons -> Some(head())
    }
}

fun <A> Stream<A>.toList(): MyList<A> {
    return when(this) {
        is Empty -> MyList.Nil
        is Cons -> MyList.Cons(
            this.head(),
            this.tail().toList()
            )
    }
}

fun <A> Stream<A>.take(n: Int): Stream<A> {
    fun go(xs: Stream<A>, pn: Int): Stream<A> {
        return when(xs) {
            is Empty -> empty()
            is Cons -> cons(
                { xs.head() },
                { if(pn != 0) { go(xs.tail(), pn - 1) } else empty() }
            )
        }
    }
    return go(this, n)
}

fun <A> Stream<A>.drop(n: Int): Stream<A> {
    fun go(xs: Stream<A>, pn: Int): Stream<A> {
        return when(xs) {
            is Empty -> empty()
            is Cons -> if(pn == 0) {
                cons({ xs.head() }, { go(xs.tail(), pn - 1 ) } )
            } else {
                go(xs.tail(), pn - 1)
            }
        }
    }
    return go(this, n)
}

fun <A> Stream<A>.takeWhile(p: (A) -> Boolean): Stream<A> {
    fun go(xs: Stream<A>, pn: (A) -> Boolean): Stream<A> {
        return when(xs) {
            is Empty -> empty()
            is Cons -> if(p(xs.head())) {
                cons(
                { xs.head() },
                { go(xs.tail(), p) }
                )
            } else {
                empty()
            }
        }
    }
    return go(this, p)
}
