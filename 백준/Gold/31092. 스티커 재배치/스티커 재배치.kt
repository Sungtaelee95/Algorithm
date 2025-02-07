import java.io.*
import java.util.*
import kotlin.math.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    val (n, m, k) = reader.readLine().split(" ").map { it.toInt() }
    val stickers = mutableListOf<Triple<String, Int, Int>>()

    repeat(m) {
        val (s, d, a) = reader.readLine().split(" ")
        stickers.add(Triple(s, d.toInt(), a.toInt()))
    }

    val b = reader.readLine().split(" ").map { it.toInt() - 1 }
    val target = reader.readLine().trim()

    val minPurchaseCost = mutableMapOf<String, Int>()
    val availableChars = mutableSetOf<String>()

    for ((s, _, a) in stickers) {
        if (s in minPurchaseCost) {
            minPurchaseCost[s] = min(minPurchaseCost[s]!!, a)
        } else {
            minPurchaseCost[s] = a
        }
        availableChars.add(s)
    }

    // Check if all characters in S are in the sticker set
    if (target.any { it.toString() !in availableChars }) {
        println(-1)
        return
    }

    var result = Int.MAX_VALUE

    for (start in 0..n - k) {
        var aCost = 0
        val off = mutableMapOf<String, PriorityQueue<Int>>()

        for (g in 0 until n) {
            val stickerIndex = b[g]
            val stickerChar = stickers[stickerIndex].first
            val removeCost = stickers[stickerIndex].second

            // Initialize priority queues
            if (stickerChar !in off) {
                off[stickerChar] = PriorityQueue()
            }

            if (g in start until start + k) {
                if (target[g - start].toString() != stickerChar && availableChars.contains(stickerChar)) {
                    off[stickerChar]!!.add(0)
                    aCost += removeCost
                }
            } else {
                if (stickerChar in availableChars) {
                    off[stickerChar]!!.add(removeCost)
                }
            }
        }

        for (g in start until start + k) {
            val targetChar = target[g - start].toString()
            val stickerIndex = b[g]
            val currentChar = stickers[stickerIndex].first

            if (targetChar != currentChar) {
                if (off[targetChar] != null && off[targetChar]!!.isNotEmpty()) {
                    val v = off[targetChar]!!.poll()
                    if (v < minPurchaseCost[targetChar]!!) {
                        aCost += v
                    } else {
                        aCost += minPurchaseCost[targetChar]!!
                        off[targetChar]!!.add(v)
                    }
                } else {
                    aCost += minPurchaseCost[targetChar]!!
                }
            }
        }

        result = min(result, aCost)
    }

    println(result)
}