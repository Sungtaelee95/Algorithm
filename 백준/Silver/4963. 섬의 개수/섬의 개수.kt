fun main() {
    val br = System.`in`.bufferedReader()
    val rows = intArrayOf(-1, -1, -1, 0, 0, 1, 1, 1)
    val cols = intArrayOf(-1, 0, 1, -1, 1, -1, 0, 1)
    while (true) {
        val (w, h) = br.readLine().split(" ").map { it.toInt() }
        if (w == 0 && h == 0) break
        val map = Array(h) { br.readLine().split(" ").map { it.toInt() } }
        val visited = Array(h) { BooleanArray(w) }
        var cnt = 0
        val dq = ArrayDeque<Node>()
        for (r in 0 until h) {
            for (c in 0 until w) {
                if (visited[r][c] || map[r][c] == 0) continue
                cnt++
                dq.add(Node(r, c))
                visited[r][c] = true
                while (dq.isNotEmpty()) {
                    val now = dq.removeFirst()
                    for (i in 0..7) {
                        val nr = now.row + rows[i]
                        val nc = now.col + cols[i]
                        if (nr < 0 || nc < 0 || nr >= h || nc >= w || map[nr][nc] == 0) continue
                        if (!visited[nr][nc]) {
                            dq.addLast(Node(nr, nc))
                            visited[nr][nc] = true
                        }
                    }
                }
            }
        }
        println(cnt)
    }
}

data class Node(val row: Int, val col: Int)