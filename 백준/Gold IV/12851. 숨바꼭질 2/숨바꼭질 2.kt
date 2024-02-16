import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.min

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    var st = StringTokenizer(br.readLine())

    val N = st.nextToken().toInt()
    val K = st.nextToken().toInt()

    val visited = IntArray(200_001) { Int.MAX_VALUE }
    val dq = ArrayDeque<Info>()
    dq.add(Info(N, 0))
    var count = 0
    while (dq.isNotEmpty()) {
        val now = dq.removeFirst()
        if (now.position == K) {
            if (now.count < visited[now.position]) {
                visited[now.position] = now.count
                count = 1
                continue
            }
            if (now.count == visited[now.position]) count++
            continue
        }
        visited[now.position] = now.count
        if (now.position+1 in 0..200_000 && now.count+1 <= visited[now.position+1]) dq.addLast(Info(now.position+1, now.count+1))
        if (now.position-1 in 0..200_000 && now.count+1 <= visited[now.position-1]) dq.addLast(Info(now.position-1, now.count+1))
        if (now.position*2 in 0..200_000 && now.count+1 <= visited[now.position*2]) dq.addLast(Info(now.position*2, now.count+1))
    }
    bw.append("${visited[K]}\n$count")

    bw.flush()
    bw.close()
}

data class Info(val position: Int, val count: Int)


