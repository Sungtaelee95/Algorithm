import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.ArrayDeque

val nextRow = intArrayOf(0, 0, 1, -1)
val nextCol = intArrayOf(-1, 1, 0, 0)
lateinit var map: Array<CharArray>

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    map = Array(12) { br.readLine().toCharArray() }
    map.reverse()

    var result = 0
    while (broken()) {
        result++
        down()
    }
    bw.append("$result")

    bw.flush()
    bw.close()
}

fun down() {
    for (col in 0 .. 5) {
        val temp = mutableListOf<Char>()
        for (row in 0 .. 11) {
            if(map[row][col] != '.') temp.add(map[row][col])
            map[row][col] = '.'
        }
        temp.forEachIndexed { index, c ->
            map[index][col] = c
        }
    }
}
fun broken(): Boolean {
    var isBroke = false
    val dq = ArrayDeque<Node>()
    for (i in 0 .. 11) {
        for (j in 0 .. 5) {
            if (map[i][j] =='.') continue
            val visit = Array(12) { BooleanArray(6) }
            visit[i][j] = true
            dq.addLast(Node(i,j))
            var cnt = 1
            while (dq.isNotEmpty()) {
                val node = dq.removeFirst()
                for (k in 0 .. 3) {
                    val nextNode = Node(node.row+nextRow[k], node.col+nextCol[k])
                    if (nextNode.row < 0 || nextNode.col < 0 || nextNode.row >= 12 || nextNode.col >= 6) continue
                    if (map[node.row][node.col] != map[nextNode.row][nextNode.col]) continue
                    if (visit[nextNode.row][nextNode.col]) continue
                    cnt++
                    visit[nextNode.row][nextNode.col] = true
                    dq.addLast(nextNode)
                }
            }
            if (cnt >= 4) {
                isBroke = true
                for (k in 0..11) {
                    for (p in 0..5) {
                        if (visit[k][p]) {
                            map[k][p] = '.'
                        }
                    }
                }
            }
        }
    }
    return isBroke
}

data class Node(val row: Int, val col: Int)