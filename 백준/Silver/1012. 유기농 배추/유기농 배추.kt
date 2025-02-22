import java.io.*

val nexts = arrayOf(
    Node(0,1),
    Node(0,-1),
    Node(1,0),
    Node(-1,0)
)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val t = br.readLine().toInt()
    repeat(t) {
        val (n,m,k) = br.readLine().split(" ").map{ it.toInt() }
        val visit = Array(n) {BooleanArray(m)}
        repeat(k) {
            val (r,c) = br.readLine().split(" ").map{ it.toInt() }
            visit[r][c] = true
        }
        var result = 0
        for (r in 0 until n) {
            for (c in 0 until m) {
                if (!visit[r][c]) continue
                result++
                val dq = ArrayDeque<Node>()
                dq.add(Node(r,c))
                visit[r][c] = false
                while(dq.isNotEmpty()) {
                    val now = dq.removeFirst()
                    for (i in 0 .. 3) {
                        val nr = now.row + nexts[i].row
                        val nc = now.col + nexts[i].col
                        if (nr < 0 || nc < 0 || nr >= n || nc >= m || !visit[nr][nc]) continue
                        visit[nr][nc] = false
                        dq.addLast(Node(nr,nc))
                    }
                }
            }
        }
        bw.appendLine("$result")
    }
    bw.flush()
    bw.close()
}

data class Node(val row: Int, val col: Int)