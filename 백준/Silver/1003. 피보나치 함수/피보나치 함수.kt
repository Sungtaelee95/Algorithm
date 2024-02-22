import java.util.*
import kotlin.collections.ArrayDeque

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    var st = StringTokenizer(br.readLine())
    val tc = st.nextToken().toInt()
    val dp0 = IntArray(41)
    val dp1 = IntArray(41)
    dp0[0] = 1
    dp0[1] = 0
    dp1[0] = 0
    dp1[1] = 1
    for (i in 2..40) {
        dp0[i] = dp0[i - 1] + dp0[i - 2]
        dp1[i] = dp1[i - 1] + dp1[i - 2]
    }

    repeat(tc) {
        val num = br.readLine().toInt()
        bw.appendLine("${dp0[num]} ${dp1[num]}")
    }

    bw.flush()
    bw.close()
}
