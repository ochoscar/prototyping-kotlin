package com.ochoscar

sealed class MyTree<out A>  {
    companion object {
        // Count nodes (leaves and branches)
        fun <A> countNodes(tree: MyTree<A>): Int {
            return when(tree) {
                is Leaf -> 1
                is Branch -> 1 + countNodes(tree.right) + countNodes(tree.left)
            }
        }

        // Maximum element in the tree
        fun <A: Comparable<A>> maximum(tree: MyTree<A>): A {
            fun go(tree: MyTree<A>, max: A?): A {
                return when(tree) {
                    is Leaf -> maxOf(tree.value, max ?: tree.value)
                    is Branch -> {
                        go(tree.right, max)
                        go(tree.left, max)
                    }
                }
            }
            return go(tree, null)
        }

        // Return the maximum path length from the root of a tree to any leaf
        fun <A> depth(tree: MyTree<A>): Int {
            return when(tree) {
                is Leaf -> 1
                is Branch -> maxOf(depth(tree.left) + 1, depth(tree.right) + 1)
            }
        }

        // Map function, applies a function to all elements in the tree
        fun <A, B> map(tree: MyTree<A>, f: (A) -> B): MyTree<B> {
            return when(tree) {
                is Leaf -> Leaf(f(tree.value))
                is Branch -> {
                    Branch<B>(map(tree.left, f), map(tree.right, f))
                }
            }
        }

        //-----------------

        // Fold function
        fun <A, B> fold(tree: MyTree<A>, f: (A) -> B, b: (B, B) -> B) : B {
            return when(tree) {
                is Leaf -> f(tree.value)
                is Branch -> {
                    val itemLeft = fold(tree.left, f, b)
                    val itemRight = fold(tree.right, f, b)
                    b(itemLeft, itemRight)
                }
            }
        }

        // Count nodes (leaves and branches) with fold
        fun <A> countNodesF(tree: MyTree<A>): Int {
            return fold(tree, { 1 }, { itemLeft, itemRight -> itemRight + itemRight })
        }

        // Maximum element in the tree with fold
        fun <A: Comparable<A>> maximumF(tree: MyTree<A>): A {
            return fold(tree, { item -> item }, { itemLeft, itemRight -> maxOf(itemLeft, itemRight)})
        }

        // Return the maximum path length from the rrot of a tree to any leaf with fold
        fun <A> depthF(tree: MyTree<A>): Int {
            return fold(tree, { 1 }, { itemLeft, itemRight -> 1 + maxOf(itemRight, itemLeft) })
        }

        // Map function, applies a function to all elements in the tree with fold
        fun <A, Y> mapF(tree: MyTree<A>, f: (A) -> Y): MyTree<Y> {
            return fold<A, MyTree<Y>>(
                tree,
                { leaf -> Leaf(f(leaf)) },
                { left, right -> Branch(left, right) }
            )
        }
    }
}

data class Leaf<A>(val value: A): MyTree<A>()

data class Branch<A>(val left: MyTree<A>, val right: MyTree<A>): MyTree<A>()
