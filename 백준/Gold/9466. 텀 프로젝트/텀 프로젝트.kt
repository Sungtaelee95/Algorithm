import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

var solo = 0
lateinit var std: IntArray
lateinit var visit: BooleanArray // 방문 처리 목적
lateinit var check: BooleanArray // 이미 끝났는지 확인할 목적

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())

    repeat(st.nextToken().toInt()) {
        val count = br.readLine().toInt()
        st = StringTokenizer(br.readLine())
        std = IntArray(count) { st.nextToken().toInt() - 1 }
        solo = count
        visit = BooleanArray(count)
        check = BooleanArray(count)
        for (i in std.indices) {
            if (!check[i]) {
                dfs(i)
            }
        }
        bw.appendLine("$solo")
    }
    bw.flush()
    bw.close()
}

fun dfs(stdNum: Int) {
    visit[stdNum] = true
    var nextStdNum = std[stdNum]
    if (visit[nextStdNum]) {
        if (!check[nextStdNum]) {
            solo--
            while (nextStdNum != stdNum) {
                nextStdNum = std[nextStdNum]
                solo--
            }
        }
    } else {
        if (!visit[nextStdNum]) dfs(nextStdNum)
    }
    check[stdNum] = true
}

