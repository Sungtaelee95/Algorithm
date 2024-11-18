import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

val dirs = listOf(Node(-1, 0), Node(1, 0), Node(0, -1), Node(0, 1))
lateinit var sharkMap: Array<Array<Shark?>>
lateinit var inputMap: Array<IntArray>
lateinit var smokeMap: Array<Array<MutableList<Smoke>>>
var n = 0
var m = 0
var k = 0
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())

    n = st.nextToken().toInt()
    m = st.nextToken().toInt()
    k = st.nextToken().toInt()

    inputMap = Array(n) {
        st = StringTokenizer(br.readLine())
        IntArray(n) { st.nextToken().toInt() }
    }
    smokeMap = Array(n) { Array(n) { mutableListOf() } }
    sharkMap = Array(n) { Array<Shark?>(n) { null } }
    val firstDirs = br.readLine().split(" ").map { it.toInt() }
    for (i in 1..m) {
        var position = Node(0, 0)
        for (r in 0 until n) {
            for (c in 0 until n) {
                if (inputMap[r][c] == i) position = Node(r, c)
            }
        }
        sharkMap[position.row][position.col] = Shark(
            i,
            position,
            firstDirs[i - 1] - 1,
            br.readLine().split(" ").map { it.toInt() },
            br.readLine().split(" ").map { it.toInt() },
            br.readLine().split(" ").map { it.toInt() },
            br.readLine().split(" ").map { it.toInt() }
        )
        smokeMap[position.row][position.col].add(Smoke(i, 0))
    }
    var flag = false
    for (i in 1..1000) {
        val newSharkMap = Array(n) { Array<Shark?>(n) { null } }
        for (r in 0 until n) {
            for (c in 0 until n) {
                sharkMap[r][c]?.let {
                    val nextShark = it.calculateNext()
                    if (newSharkMap[nextShark.node.row][nextShark.node.col] != null) {
                        newSharkMap[nextShark.node.row][nextShark.node.col] =
                            newSharkMap[nextShark.node.row][nextShark.node.col]!!.fight(nextShark)
                    } else {
                        newSharkMap[nextShark.node.row][nextShark.node.col] = nextShark
                    }
                } ?: continue
            }
        }

        for (r in 0 until n) {
            for (c in 0 until n) {
                if (newSharkMap[r][c] != null) {
                    smokeMap[r][c].add(Smoke(newSharkMap[r][c]!!.id, i))
                }
                smokeMap[r][c] = smokeMap[r][c].filter { i - it.time < k }.toMutableList()
            }
        }
        var count = 0
        for (r in 0 until n) {
            for (c in 0 until n) {
                if (newSharkMap[r][c] != null) count++
            }
        }
        if (count == 1) {
            bw.append("$i")
            flag = true
            break
        }
        sharkMap = newSharkMap
    }

    if (!flag) bw.append("-1")

    bw.flush()
    bw.close()
}

data class Node(val row: Int, val col: Int)
data class Smoke(val id: Int, val time: Int)

class Shark(
    val id: Int,
    val node: Node,
    private val dir: Int,
    private val upPri: List<Int>,
    private val downPri: List<Int>,
    private val leftPri: List<Int>,
    private val rightPri: List<Int>
) {
    fun fight(otherShark: Shark): Shark {
        if (id < otherShark.id) return this
        return otherShark
    }

    fun calculateNext(): Shark {
        // 냄새 없는 곳 먼저 확인
        for (i in 0..3) {
            val pri = when (dir) {
                0 -> upPri
                1 -> downPri
                2 -> leftPri
                3 -> rightPri
                else -> listOf(0)
            }
            val nr = node.row + dirs[pri[i] - 1].row
            val nc = node.col + dirs[pri[i] - 1].col
            if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue
            if (smokeMap[nr][nc].isEmpty()) {
                return Shark(
                    id,
                    Node(nr, nc),
                    pri[i] - 1,
                    upPri,
                    downPri,
                    leftPri,
                    rightPri
                )
            }
        }
        // 냄새가 안나는 곳이 없는 경우
        for (i in 0..3) {
            val pri = when (dir) {
                0 -> upPri
                1 -> downPri
                2 -> leftPri
                3 -> rightPri
                else -> listOf(0)
            }
            val nr = node.row + dirs[pri[i] - 1].row
            val nc = node.col + dirs[pri[i] - 1].col
            if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue
            if (smokeMap[nr][nc].find { it.id == this.id } != null) {
                return Shark(
                    this.id,
                    Node(nr, nc),
                    pri[i] - 1,
                    upPri,
                    downPri,
                    leftPri,
                    rightPri
                )
            }
        }
        // 냄새가 안나는 곳에 자신의 냄새가 안나는 곳은 없다?
        return this
    }
}