import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

lateinit var ori: Array<IntArray>
var n = 0
var b = 0L
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())

    n = st.nextToken().toInt()
    b = st.nextToken().toLong()
    ori = Array(n) { IntArray(n) }

    repeat(n) { row ->
        st = StringTokenizer(br.readLine())
        repeat(n) { col ->
            ori[row][col] = st.nextToken().toInt() % 1_000
        }
    }
    val result = pow(ori, b)

    result.forEach {
        it.forEach {
            bw.append("$it ")
        }
        bw.appendLine()
    }

    bw.flush()
    bw.close()
}

fun pow(arr: Array<IntArray>, count: Long): Array<IntArray> {
    if (count == 1L) return arr
    var pows = pow(arr, count / 2)
    pows = mult(pows, pows)
    if (count % 2 == 1L) {
        pows = mult(pows, ori)
    }
    return pows
}

fun mult(a1: Array<IntArray>, a2: Array<IntArray>): Array<IntArray> {
    val result = Array(n) { IntArray(n) }
    for (i in 0 until n) {
        for (j in 0 until n) {
            for (k in 0 until n) {
                result[i][j] += a1[i][k] * a2[k][j]
                result[i][j] %= 1_000
            }
        }
    }
    return result
}