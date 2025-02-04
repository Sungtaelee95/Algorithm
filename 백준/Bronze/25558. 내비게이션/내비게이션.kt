import java.io.*
import kotlin.math.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val n = br.readLine().toInt()
    val (s1, s2, e1, e2) = br.readLine().split(" ").map { it.toLong() }
    var maxLength = Long.MAX_VALUE
    var result = 0
    repeat(n) { number ->
        val m = br.readLine().toInt()
        val nodes = Array<Node>(m + 2) { Node(0, 0) }
        nodes[0] = Node(s1, s2)
        nodes[m + 1] = Node(e1, e2)
        repeat(m) {
            val (x, y) = br.readLine().split(" ").map { it.toLong() }
            nodes[it + 1] = Node(x, y)
        }
        var length = 0L
        for (i in 1 until nodes.size) {
            val x1 = nodes[i - 1].x
            val y1 = nodes[i - 1].y
            val x2 = nodes[i].x
            val y2 = nodes[i].y
            length += abs(x1 - x2) + abs(y1 - y2)
        }
        if (maxLength > length) {
            maxLength = length
            result = number + 1
        }
    }
    bw.append("$result")
    bw.flush()
    bw.close()
}

data class Node(val x: Long, val y: Long)