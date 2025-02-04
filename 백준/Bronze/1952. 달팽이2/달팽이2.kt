import java.io.*

val rows = intArrayOf(0,1,0,-1)
val cols = intArrayOf(1,0,-1,0)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (r,c) = br.readLine().split(" ").map { it.toInt() }
    val map = Array(r) {BooleanArray(c)}
    map[0][0] = true
    var dire = 0
    var cnt = -1
    val dq = ArrayDeque<Node>()
    dq.add(Node(0,0))
    while(dq.isNotEmpty()) {
        val now = dq.removeFirst()
        map[now.row][now.col] = true
        var nr = now.row + rows[dire]
        var nc = now.col + cols[dire]
        if (nr < 0 || nc < 0 || nr >= r || nc >= c || map[nr][nc]) {
            dire = ++dire % 4
            nr = now.row + rows[dire]
            nc = now.col + cols[dire]
            cnt++
        }
        if (!map[nr][nc]) dq.add(Node(nr,nc))
    }
    bw.append("$cnt")
    bw.flush()
    bw.close()
}

data class Node(val row: Int, val col: Int)