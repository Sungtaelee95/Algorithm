import java.io.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (n, k) = br.readLine().split(" ").map { it.toInt() }
    val visit = BooleanArray(100001)
    val dq = ArrayDeque<Node>()
    var result = Int.MAX_VALUE
    dq.addFirst(Node(n, 0))
    while (dq.isNotEmpty()) {
        val now = dq.removeFirst()
        if (now.pos == k) {
            result = minOf(result, now.time)
            break
        }

        val douPos = now.pos * 2
        val minusPos = now.pos - 1
        val plusPos = now.pos + 1

        if (douPos <= 100000 && !visit[douPos]) {
            visit[douPos] = true
            dq.addLast(Node(douPos, now.time))
        }

        if (minusPos >= 0 && !visit[minusPos]) {
            visit[minusPos] = true
            dq.addLast(Node(minusPos, now.time + 1))
        }

        if (plusPos <= 100000 && !visit[plusPos]) {
            visit[plusPos] = true
            dq.addLast(Node(plusPos, now.time + 1))
        }
    }
    bw.append("$result")
    bw.flush()
    bw.close()
}

data class Node(val pos: Int, val time: Int)