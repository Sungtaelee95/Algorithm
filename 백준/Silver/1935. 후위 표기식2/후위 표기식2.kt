import java.io.BufferedReader // ktlint-disable filename
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.floor

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())

    val N = st.nextToken().toInt()
    val str = br.readLine()
    val matchingMap = mutableMapOf<Char, Double>()
    repeat(N) {
        matchingMap['A' + it] = br.readLine().toDouble()
    }

    val numDq = ArrayDeque<Double>()

    for (s in str) {
        if (matchingMap.containsKey(s)) {
            numDq.addLast(matchingMap[s]!!)
            continue
        }
        val second = numDq.pollLast()
        val first = numDq.pollLast()
        when (s) {
            '+' -> {
                numDq.addLast(first + second)
            }

            '-' -> {
                numDq.addLast(first - second)
            }

            '*' -> {
                numDq.addLast(first * second)
            }

            '/' -> {
                numDq.addLast(first / second)
            }
        }
    }

    bw.append(String.format("%.2f", floor(numDq.pollLast() * 1000) / 1000))

    bw.flush()
    bw.close()
}
