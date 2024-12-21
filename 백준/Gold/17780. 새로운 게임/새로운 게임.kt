import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayDeque

lateinit var map: Array<IntArray>
lateinit var pieceMap: Array<Array<ArrayDeque<Piece>>>
const val WHITE = 0
const val RED = 1
const val BLUE = 2
var n = 0
var k = 0
val rows = intArrayOf(0, 0, -1, 1)
val cols = intArrayOf(1, -1, 0, 0)
val pieces = mutableListOf<Piece>()
var result = 1

//→, ←, ↑, ↓
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    n = st.nextToken().toInt()
    k = st.nextToken().toInt()
    map = Array(n) {
        br.readLine().split(" ").map { it.toInt() }.toIntArray()
    }
    pieceMap = Array(n) { Array(n) { ArrayDeque() } }
    repeat(k) {
        val (r, c, d) = br.readLine().split(" ").map { it.toInt() }
        pieces.add(Piece(it, r - 1, c - 1, d - 1))
        pieceMap[r - 1][c - 1].add(Piece(it, r - 1, c - 1, d - 1))
    }
    while (true) {
        if (result > 1000) break
        if (simulate()) break
        result++
    }

    if (result > 1000) bw.append("-1")
    else bw.append("$result")

    bw.flush()
    bw.close()
}

private fun simulate(): Boolean {
    for (i in pieces.indices) {
        val ori = pieces[i]
        if (pieceMap[ori.row][ori.col].first() != ori) continue // 처음꺼만 움직인다......
        if (nextPositionCheck(pieces[i])) {
            changeDist(pieces[i])
        }
        if (!nextPositionCheck(pieces[i])) {
            val movePieces = findMovePieces(pieces[i])
            movePiece(movePieces)
        }
        if (endCheck()) break
    }
    return endCheck()
}

fun movePiece(piecesDq: ArrayDeque<Piece>) {
    val fp = piecesDq.first()
    val nr = fp.row + rows[fp.dist]
    val nc = fp.col + cols[fp.dist]
    when (map[nr][nc]) {
        WHITE -> {
            while (piecesDq.isNotEmpty()) {
                val ori = piecesDq.removeFirst()
                val movePiece = Piece(ori.id, nr, nc, ori.dist)
                updatePiece(movePiece)
                pieceMap[nr][nc].addLast(movePiece)
            }
        }

        RED -> {
            while (piecesDq.isNotEmpty()) {
                val ori = piecesDq.removeLast()
                val movePiece = Piece(ori.id, nr, nc, ori.dist)
                updatePiece(movePiece)
                pieceMap[nr][nc].addLast(movePiece)
            }
        }
    }
}

fun findMovePieces(piece: Piece): ArrayDeque<Piece> {
    val dq = ArrayDeque<Piece>()
    while (true) {
        val p = pieceMap[piece.row][piece.col].removeLast()
        dq.addFirst(p)
        if (p.id == piece.id) break
    }
    return dq
}

private fun nextPositionCheck(piece: Piece): Boolean {
    val nr = piece.row + rows[piece.dist]
    val nc = piece.col + cols[piece.dist]
    return nr < 0 || nc < 0 || nr >= n || nc >= n || map[nr][nc] == BLUE
}

private fun changeDist(piece: Piece) {
    val change = when (piece.dist) {
        0 -> piece.copy(dist = 1)
        1 -> piece.copy(dist = 0)
        2 -> piece.copy(dist = 3)
        else -> piece.copy(dist = 2)
    }
    pieceMapUpdate(change)
    updatePiece(change)
}

private fun updatePiece(piece: Piece) {
    for (i in pieces.indices) {
        if (pieces[i].id == piece.id) {
            pieces[i] = piece
            break
        }
    }
}

private fun pieceMapUpdate(piece: Piece) {
    val temp = ArrayDeque<Piece>()
    while (pieceMap[piece.row][piece.col].isNotEmpty()) {
        val ori = pieceMap[piece.row][piece.col].removeLast()
        if (ori.id == piece.id) {
            temp.addFirst(piece)
            continue
        }
        temp.addFirst(ori)
    }
    pieceMap[piece.row][piece.col] = temp
}

private fun endCheck(): Boolean {
    pieceMap.forEach {
        it.forEach {
            if (it.size >= 4) return true
        }
    }
    return false
}

data class Piece(val id: Int, val row: Int, val col: Int, val dist: Int)
