import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())

    val m = st.nextToken().toInt()
    val n = st.nextToken().toInt()

    val bug = Array(m) { IntArray(m) { 1 } } // 벌레들
    val up = IntArray(2 * m - 1) { 0 } // 성장기록
    repeat(n) {
        st = StringTokenizer(br.readLine())
        val zero = st.nextToken().toInt()
        val one = st.nextToken().toInt()
        val two = st.nextToken().toInt()
        for (i in zero until zero + one) {
            up[i]++
        }
        for (i in zero + one until 2 * m - 1) {
            up[i] += 2
        }
    }
    var index = 0
    for (i in m - 1 downTo 0) {
        bug[i][0] += up[index]
        index++
    }
    for (i in 1 until m) {
        bug[0][i] += up[index]
        index++
    }
    for (col in 1 until m) {
        for (i in 1 until m) {
            bug[i][col] = bug[0][col]
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