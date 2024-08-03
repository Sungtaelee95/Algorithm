import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    val input = Array(n) {
        st = StringTokenizer(br.readLine())
        IntArray(m) { st.nextToken().toInt() }
    }

    var count = Int.MAX_VALUE
    for (joker in 0 until n) {
        var cnt = 0
        val used = BooleanArray(m)
        for (i in 0 until n) {
            if (i == joker) continue
            val colorCnt = input[i].count { it != 0 }
            when (colorCnt) {
                0 -> continue
                1 -> {
                    for (j in 0 until m) {
                        if (input[i][j] != 0) {
                            if (used[j]) {
                                cnt++
                                break
                            }
                            used[j] = true
                        }
                    }
                }
                else -> {
                    cnt++
                }
            }
        }
        count = minOf(count, cnt)
    }

    bw.append("$count")

    bw.flush()
    bw.close()
}