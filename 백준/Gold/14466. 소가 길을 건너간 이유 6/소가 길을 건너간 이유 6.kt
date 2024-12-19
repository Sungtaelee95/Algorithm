import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

var result = 0

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (n, k, r) = br.readLine().split(" ").map { it.toInt() }
    result = k * (k - 1)

    val loads = Array(n + 1) { Array(n + 1) { mutableListOf<Load>() } }
    repeat(r) {
        val (r1, c1, r2, c2) = br.readLine().split(" ").map { it.toInt() }
        loads[r1][c1].add(Load(r2, c2))
        loads[r2][c2].add(Load(r1, c1))
    }
    val cows = mutableListOf<Cow>()
    repeat(k) {
        val (row, col) = br.readLine().split(" ").map { it.toInt() }
        cows.add(Cow(row, col))
    }

    cows.forEach {
        val map = Array(n + 1) { BooleanArray(n + 1) }
        bfs(map, loads, it, cows)
    }

    bw.append("${result / 2}")

    bw.flush()
    bw.close()
}

val rows = intArrayOf(0, 0, 1, -1)
val cols = intArrayOf(1, -1, 0, 0)

fun bfs(map: Array<BooleanArray>, loads: Array<Array<MutableList<Load>>>, cow: Cow, cows: List<Cow>) {
    val dq = ArrayDeque<Cow>()
    dq.add(cow)
    map[cow.row][cow.col] = true
    while (dq.isNotEmpty()) {
        val now = dq.removeFirst()
        for (i in 0..3) {
            val nr = now.row + rows[i]
            val nc = now.col + cols[i]
            if (nr <= 0 || nc <= 0 || nr >= map.size || nc >= map.size) continue
            if (map[nr][nc]) continue
            if (loads[now.row][now.col].contains(Load(nr, nc))) continue
            if (cows.contains(Cow(nr, nc))) result--
            dq.addLast(Cow(nr, nc))
            map[nr][nc] = true
        }
    }
}

data class Load(val row: Int, val col: Int)
data class Cow(val row: Int, val col: Int)
