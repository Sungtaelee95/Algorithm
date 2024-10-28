import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.util.*

val dx = arrayOf(1, -1, 0, 0)
val dy = arrayOf(0, 0, 1, -1)

lateinit var visited: Array<BooleanArray>
lateinit var map: Array<IntArray>
var max = 0
var squareN = 0
var cnt = 0

fun main() = with(System.`in`.bufferedReader()) {
    val (n, q) = readLine().split(" ").map { it.toInt() }
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    squareN = 1.shl(n)
    map = Array(squareN) {
        val st = StringTokenizer(readLine())
        IntArray(squareN) { st.nextToken().toInt() }
    }

    val st = StringTokenizer(readLine())
    repeat(q) {
        val l = st.nextToken().toInt()

        val size = 1.shl(l)

        val copiedMap = Array(squareN) { IntArray(squareN) { 0 } }

        for (startX in 0 until squareN step size) {
            for (startY in 0 until squareN step size) {
                for (i in 0 until size) {
                    for (j in 0 until size) {
                        copiedMap[i + startX][j + startY] = map[size - 1 - j + startX][i + startY]
                    }
                }
            }
        }

        val minus = Array(squareN) { BooleanArray(squareN) { false } }

        repeat(squareN) { x ->
            repeat(squareN) { y ->
                var cnt = 0

                for (i in 0 until 4) {
                    val nx = x + dx[i]
                    val ny = y + dy[i]

                    if (nx !in 0 until squareN || ny !in 0 until squareN || copiedMap[nx][ny] == 0) continue
                    cnt++
                }

                if (cnt < 3) minus[x][y] = true
            }
        }

        repeat(squareN) { x ->
            repeat(squareN) { y ->
                map[x][y] = copiedMap[x][y]
                if (minus[x][y] && map[x][y] > 0) map[x][y]--
            }
        }
    }

    visited = Array(squareN) { BooleanArray(squareN) { false } }

    val sum = map.sumOf { it.sum() }
    repeat(squareN) { x ->
        repeat(squareN) { y ->
            dfs(x, y)
            cnt = 0
        }
    }
    bw.append("$sum\n$max")

    bw.flush()
    bw.close()
}

fun dfs(x: Int, y: Int) {
    if (map[x][y] == 0 || visited[x][y]) return
    visited[x][y] = true
    cnt++
    max = maxOf(max, cnt)

    for (i in 0 until 4) {
        val nx = x + dx[i]
        val ny = y + dy[i]

        if (nx !in 0 until squareN || ny !in 0 until squareN) continue

        dfs(nx, ny)
    }
}