import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (n, m, l, k) = br.readLine().split(" ").map { it.toInt() }
    val stars = mutableListOf<Star>()

    repeat(k) {
        val (r, c) = br.readLine().split(" ").map { it.toInt() }
        stars.add(Star(r, c))
    }

    var result = Int.MAX_VALUE

    for (s1 in stars) {
        for (s2 in stars) {
            val sr = s1.row
            val sc = s2.col
            val count = stars.count {
                it.row in sr .. sr + l && it.col in sc .. sc + l
            }
            result = minOf(result, k - count)
        }
    }
    bw.append("$result")

    bw.flush()
    bw.close()
}

data class Star(val row: Int, val col: Int)
