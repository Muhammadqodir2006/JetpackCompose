package com.example.jetpackcompose.util

import kotlin.random.Random

class Generator private constructor(
    var var1: Int,
    var var2: Int,
    var result: Int,
    var symbol: Int
) {

    companion object {
        fun generate(level: Int): Generator {
            var limit = 10
            if (level == 1) limit = 20
            if (level == 2) limit = 50
            var v1 = Random.nextInt(1, limit)
            val v2 = Random.nextInt(1, limit)
            val symbol = Random.nextInt(4)
            var res = when (symbol) {
                0 -> v1 - v2
                1 -> v1 + v2
                else -> v1 * v2
            }
            if (symbol == 3) {
                val k = v1
                v1 = res
                res = k
            }
            return Generator(v1, v2, res, symbol)
        }
    }
}