import Result
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

lateinit var city: IntArray
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val n = br.readLine().toInt()

    val inputs = mutableListOf<String>()
    repeat(n) {
        inputs.add(br.readLine())
    }
    val input = inputs.distinct()
    val result = Result("", "")
    var score = 0

    for (i in 0 until input.size-1) {
        val one = input[i]
        for (j in i+1 until input.size) {
            val two = input[j]
            var tempScore = 0
            val length = minOf(one.length, two.length)
            for (k in 0 until length) {
                if (one[k] != two[k]) break
                tempScore++
            }
            if (tempScore > score) {
                score = tempScore
                result.one = one
                result.two = two
            }
        }
    }

    bw.append("${result.one}\n${result.two}")

    bw.flush()
    bw.close()
}

data class Result(var one: String, var two: String)
