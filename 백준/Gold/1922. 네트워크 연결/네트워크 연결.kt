import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*


fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val n = br.readLine().toInt()
    val m = br.readLine().toInt()
    val coms = Array(n) { mutableListOf<Node>() }
    val visit = BooleanArray(n)
    val pq = PriorityQueue<Node>(kotlin.Comparator { o1, o2 ->
        if (o1.c == o2.c) o1.w.compareTo(o2.w) else o1.c.compareTo(o2.c)
    })
    repeat(m) {
        val st = StringTokenizer(br.readLine())
        val a = st.nextToken().toInt() - 1
        val b = st.nextToken().toInt() - 1
        val c = st.nextToken().toInt()
        coms[a].add(Node(b, c))
        coms[b].add(Node(a, c))
    }
    pq.add(Node(0, 0))
    var total = 0
    while (pq.isNotEmpty()) {
        val now = pq.poll()
        if (visit[now.w]) continue
        visit[now.w] = true
        total += now.c
        for (nextNode in coms[now.w]) {
            if (!visit[nextNode.w]) pq.add(nextNode)
        }
    }

    bw.append("$total")

    bw.flush()
    bw.close()
}

data class Node(val w: Int, val c: Int)