import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

lateinit var map: Array<Array<Shark?>>
var r = 0
var c = 0

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())

    r = st.nextToken().toInt()
    c = st.nextToken().toInt()
    map = Array(r) { Array(c) { null } }
    val sharkCount = st.nextToken().toInt()
    repeat(sharkCount) {
        st = StringTokenizer(br.readLine())
        val row = st.nextToken().toInt() - 1
        val col = st.nextToken().toInt() - 1
        val shark = Shark(
            row,
            col,
            st.nextToken().toInt(),
            st.nextToken().toInt(),
            st.nextToken().toInt(),
        )
        map[row][col] = shark
    }

    var result = 0
    repeat(c) {
        // 상어 잡음
        for (row in 0 until r) {
            if (map[row][it] != null) {
                result += map[row][it]!!.size
                map[row][it] = null
                break
            }
        }
        // 상어 이동
        val moveMap = Array(r) { Array<Shark?>(c) { null } }
        for (row in 0 until r) {
            for (col in 0 until c) {
                if (map[row][col] != null) {
                    val moveNode = map[row][col]!!.move()
                    if (moveMap[moveNode.row][moveNode.col] != null) {
                        if (moveMap[moveNode.row][moveNode.col]!!.size < map[row][col]!!.size) {
                            moveMap[moveNode.row][moveNode.col] = map[row][col]
                        }
                    } else {
                        moveMap[moveNode.row][moveNode.col] = map[row][col]
                    }
                }
            }
        }
        // 맵 갱신
        map = moveMap
    }

    bw.append("$result")

    bw.flush()
    bw.close()
}

val rows = intArrayOf(0, -1, 1, 0, 0)
val cols = intArrayOf(0, 0, 0, 1, -1)

class Shark(
    var row: Int,
    var col: Int,
    val speed: Int,
    var dist: Int,
    val size: Int
) {
    fun move(): Node {
        val s = when (dist) {
            1, 2 -> speed % ((r - 1) * 2)
            3, 4 -> speed % ((c - 1) * 2)
            else -> 0
        }
        repeat(s) {
            row += rows[dist]
            col += cols[dist]
            if (row < 0 || row >= r || col < 0 || col >= c) {
                dist = changeDist()
                row += rows[dist] * 2
                col += cols[dist] * 2
            }
        }
        return Node(row, col)
    }

    private fun changeDist() = when (dist) {
        1 -> 2
        2 -> 1
        3 -> 4
        4 -> 3
        else -> 0
    }
}

data class Node(val row: Int, val col: Int)