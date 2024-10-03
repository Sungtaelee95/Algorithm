import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayDeque

lateinit var map: Array<Array<Board>>
var n = 0
const val WHITE = 0
const val RED = 1
const val BLUE = 2
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())

    n = st.nextToken().toInt()
    val k = st.nextToken().toInt()

    map = Array(n) {
        st = StringTokenizer(br.readLine())
        Array(n) { Board(st.nextToken().toInt(), ArrayDeque()) }
    }

    repeat(k) {
        st = StringTokenizer(br.readLine())
        val piece = Piece(
            st.nextToken().toInt() - 1,
            st.nextToken().toInt() - 1,
            st.nextToken().toInt(),
            it
        )
        map[piece.row][piece.col].dq.addLast(piece)
    }
    var flag = false
    for (i in 1..1000) {
        for (j in 0 until k) {
            val dq = ArrayDeque<Piece>()
            // 이동할 말 추출
            for (r in 0 until n) {
                for (c in 0 until n) {
                    while (map[r][c].dq.any { it.num == j }) {
                        dq.addFirst(map[r][c].dq.removeLast())
                    }
                }
            }
            // 이동할 위치 확인
            val nextNode = dq.first().getNextPosition()
            // 벽이든 파란칸 양쪽이라서 못움직이는 경우
            if (!dq.first().movePossible) {
                dq.first().movePossible = true
                while (dq.isNotEmpty()) {
                    map[nextNode.row][nextNode.col].dq.addLast(dq.removeFirst())
                }
            } else {
                // 이동 처리
                while (dq.isNotEmpty()) {
                    val movePiece = when (map[nextNode.row][nextNode.col].color) {
                        RED -> dq.removeLast()
                        WHITE -> dq.removeFirst()
                        else -> Piece(0, 0, 0, 0)
                    }
                    movePiece.row = nextNode.row
                    movePiece.col = nextNode.col
                    map[nextNode.row][nextNode.col].dq.addLast(movePiece)
                }
            }
            if (map[nextNode.row][nextNode.col].dq.size >= 4) {
                flag = true
                break
            }
        }
        if (flag) {
            bw.append("$i")
            break
        }
    }
    if (!flag) bw.append("-1")

    bw.flush()
    bw.close()
}

data class Node(val row: Int, val col: Int)
data class Board(val color: Int, val dq: ArrayDeque<Piece>)

val rows = intArrayOf(0, 0, 0, -1, 1)
val cols = intArrayOf(0, 1, -1, 0, 0)

data class Piece(var row: Int, var col: Int, var direction: Int, val num: Int, var movePossible: Boolean = true) {

    fun getNextPosition(): Node {
        var nr = row + rows[direction]
        var nc = col + cols[direction]
        if (nr < 0 || nc < 0 || nr >= n || nc >= n || map[nr][nc].color == BLUE) {
            nr -= rows[direction]
            nc -= cols[direction]
            changeDirection()
            nr += rows[direction]
            nc += cols[direction]
            if (nr < 0 || nc < 0 || nr >= n || nc >= n || map[nr][nc].color == BLUE) {
                nr -= rows[direction]
                nc -= cols[direction]
                movePossible = false
            }
        }
        return Node(nr, nc)
    }

    private fun changeDirection() {
        when (direction) {
            1 -> direction = 2
            2 -> direction = 1
            3 -> direction = 4
            4 -> direction = 3
        }
    }
}