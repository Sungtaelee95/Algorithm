import java.util.*

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    var st = StringTokenizer(br.readLine())

    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    val dist = IntArray(n + 1) { Int.MAX_VALUE }
    val visit = BooleanArray(n + 1)
    val nodeList = Array(n + 1) { mutableListOf<Info>() }

    repeat(m) {
        st = StringTokenizer(br.readLine())
        val s = st.nextToken().toInt()
        val e = st.nextToken().toInt()
        val d = st.nextToken().toInt()
        nodeList[s].add(Info(e, d))
        nodeList[e].add(Info(s, d))
    }

    val pq = PriorityQueue<Pair<Int, Int>> { o1, o2 -> o1.first.compareTo(o2.first) }

    pq.add(Pair(0, 1)) // 가중치와 현재 위치
    dist[1] = 0

    while (!pq.isEmpty()) {
        val info = pq.poll()
        val now = info.second
        if (visit[now]) continue
        visit[now] = true

        for (i in nodeList[now]) {
            val target = i.target
            val current = i.current
            if (dist[target] > dist[now] + current) {
                dist[target] = dist[now] + current
                pq.add(Pair(dist[target],target))
            }
        }

    }

    bw.append("${dist[n]}")

    bw.flush()
    bw.close()
}

data class Info(
    val target: Int, // 다음 정점
    val current: Int // 가중치
)