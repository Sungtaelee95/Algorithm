import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())

    val n = st.nextToken().toInt() + 1
    val m = st.nextToken().toInt()

    val map = Array(n) { LongArray(n) { 20_000_000_000L } }

    for (i in 0 until n) {
        map[i][i] = 0
    }

    repeat(m) {
        st = StringTokenizer(br.readLine())
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        val c = st.nextToken().toLong()
        map[a][b] = minOf(map[a][b], c)
    }

    for (k in 1 until n) {
        for (j in 1 until n) {
            for (i in 1 until n) {
                if (map[i][k] == 20_000_000_000L || map[k][j] == 20_000_000_000L) continue
                map[i][j] = minOf(map[i][j], map[i][k] + map[k][j])
            }
        }
    }

    var check = false

    for (i in 0 until n) {
        if (map[i][i] < 0L && map[1][i] != 20_000_000_000L) check = true
    }

    if (check) {
        bw.append("-1")
    } else {
        for (i in 2 until n) {
            if (map[1][i] == 20_000_000_000L) {
                bw.appendLine("-1")
            } else {
                bw.appendLine("${map[1][i]}")
            }
        }
    }

    bw.flush()
    bw.close()
}
