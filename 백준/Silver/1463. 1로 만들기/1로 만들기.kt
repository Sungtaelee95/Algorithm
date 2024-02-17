import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.min

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    var st = StringTokenizer(br.readLine())
    val visit = BooleanArray(1_000_001)
    val N = st.nextToken().toInt()
    val dq = ArrayDeque<Info>()
    var answer = Int.MAX_VALUE
    dq.add(Info(N, 0))
    while (dq.isNotEmpty()) {
        val now = dq.removeFirst()
        if (now.num == 1) {
            answer = min(answer, now.count)
            continue
        }
        if (now.num % 3 == 0 && !visit[now.num / 3]) {
            dq.add(Info(now.num / 3, now.count + 1))
            visit[now.num / 3] = true
        }
        if (now.num % 2 == 0 && !visit[now.num / 2]) {
            dq.add(Info(now.num / 2, now.count + 1))
            visit[now.num / 2] = true
        }
        if (!visit[now.num - 1]) {
            visit[now.num - 1] = true
            dq.add(Info(now.num - 1, now.count + 1))
        }
    }

    bw.append("$answer")

    bw.flush()
    bw.close()
}

data class Info(val num: Int, val count: Int)