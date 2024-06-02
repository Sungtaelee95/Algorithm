import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayDeque

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())

    val m = st.nextToken().toInt()
    val n = st.nextToken().toInt()

    val bug = Array(m) { IntArray(m) { 1 } } // 벌레들

    repeat(n) {
        val up = Array(m) { IntArray(m) { 0 } } // 성장기록
        st = StringTokenizer(br.readLine())
        val dq = ArrayDeque<Int>()
        repeat(st.nextToken().toInt()) { dq.addLast(0) }
        repeat(st.nextToken().toInt()) { dq.addLast(1) }
        repeat(st.nextToken().toInt()) { dq.addLast(2) }
        for (i in m - 1 downTo 0) {
            up[i][0] = dq.removeFirst()
            bug[i][0] += up[i][0]
        }
        for (i in 1 until m) {
            up[0][i] = dq.removeFirst()
            bug[0][i] += up[0][i]
        }
        for (row in 1 until m) {
            for (col in 1 until m) {
                up[row][col] = maxOf(up[row][col - 1], maxOf(up[row - 1][col - 1], up[row - 1][col]))
                bug[row][col] += up[row][col]
            }
        }
    }

    bug.forEach {
        it.forEach {
            bw.append("$it ")
        }
        bw.appendLine()
    }

    bw.flush()
    bw.close()
}
