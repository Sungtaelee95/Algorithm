import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayDeque

// 방향 상수
const val RIGHT = 1
const val LEFT = 2
const val UP = 3
const val DOWN = 4

const val NONE = 0
const val CHECK_POINT = 5

// 방향
val rightNode = Node(0, 1)
val leftNode = Node(0, -1)
val upNode = Node(-1, 0)
val downNode = Node(1, 0)

// 방향에 따른 바람
val rightAndLeftSideWind = arrayOf(Node(1, 0), Node(-1, 0))
val upAndDownSideWind = arrayOf(Node(0, 1), Node(0, -1))

val rows = intArrayOf(0, 0, 1, -1)
val cols = intArrayOf(1, -1, 0, 0)

var r = 0
var c = 0
var k = 0

lateinit var map: Array<IntArray>
lateinit var block_map: Array<Array<MutableList<Node>>>
lateinit var template_map: Array<IntArray>

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    r = st.nextToken().toInt()
    c = st.nextToken().toInt()
    k = st.nextToken().toInt()

    map = Array(r) { br.readLine().split(" ").map { it.toInt() }.toIntArray() }
    block_map = Array(r) { Array(c) { mutableListOf<Node>() } }
    template_map = Array(r) { IntArray(c) }

    repeat(br.readLine().toInt()) {
        var (x, y, t) = br.readLine().split(" ").map { it.toInt() }
        --x
        --y
        when (t) {
            0 -> {
                block_map[x][y].add(Node(x - 1, y))
                block_map[x - 1][y].add(Node(x, y))
            }

            1 -> {
                block_map[x][y].add(Node(x, y + 1))
                block_map[x][y + 1].add(Node(x, y))
            }
        }
    }
    var result = 0

    for (i in 1..101) {
        hitterOn()
        templateControlled()
        sideTemplateDown()
        result = i
        if (endCheck()) break
    }

    bw.append("$result")

    bw.flush()
    bw.close()
}

private fun endCheck(): Boolean {
    for (row in 0 until r) {
        for (col in 0 until c) {
            if (map[row][col] == CHECK_POINT) {
                if (template_map[row][col] < k) return false
            }
        }
    }
    return true
}

private fun templateControlled() {
    val newTemplateMap = Array(r) { IntArray(c) }
    for (row in 0 until r) {
        for (col in 0 until c) {
            if (template_map[row][col] == NONE) continue
            var sumSubTemplate = 0
            for (i in 0..3) {
                val nr = row + rows[i]
                val nc = col + cols[i]
                if (nr < 0 || nc < 0 || nr >= r || nc >= c) continue
                if (block_map[row][col].contains(Node(nr, nc))) continue
                if (template_map[row][col] < template_map[nr][nc]) continue
                val subTemplate = (template_map[row][col] - template_map[nr][nc]) / 4
                sumSubTemplate += subTemplate
                newTemplateMap[nr][nc] += subTemplate
            }
            newTemplateMap[row][col] += template_map[row][col] - sumSubTemplate
        }
    }
    template_map = newTemplateMap
}

private fun sideTemplateDown() {
    var startCol = 0
    for (col in 0 until c) {
        var sum = 0
        for (row in 0 until r) {
            sum += template_map[row][col]
        }
        if (sum > 0) {
            startCol = col
            break
        }
    }

    var endCol = 0
    for (col in c - 1 downTo 0) {
        var sum = 0
        for (row in 0 until r) {
            sum += template_map[row][col]
        }
        if (sum > 0) {
            endCol = col
            break
        }
    }

    for (row in 0 until r) {
        for (col in 0 until c) {
            if (row == 0 || row == r-1 || col == startCol || col == endCol) {
                if (template_map[row][col] > 0) template_map[row][col]--
            }
        }
    }

}

private fun hitterOn() {
    val templateUpMap = Array(r) { IntArray(c) }

    for (row in 0 until r) {
        for (col in 0 until c) {
            if (map[row][col] == NONE) continue
            val upHistory = when (map[row][col]) {
                RIGHT -> {
                    makeUpHistory(Node(row + rightNode.row, col + rightNode.col), rightNode, rightAndLeftSideWind)
                }

                LEFT -> {
                    makeUpHistory(Node(row + leftNode.row, col + leftNode.col), leftNode, rightAndLeftSideWind)
                }

                UP -> {
                    makeUpHistory(Node(row + upNode.row, col + upNode.col), upNode, upAndDownSideWind)
                }

                DOWN -> {
                    makeUpHistory(Node(row + downNode.row, col + downNode.col), downNode, upAndDownSideWind)
                }

                else -> {
                    Array(r) { IntArray(c) }
                }
            }
            upHistory.forEachIndexed { index, ints ->
                ints.forEachIndexed { index2, i ->
                    templateUpMap[index][index2] += i
                }
            }
        }
    }
    templateUpMap.forEachIndexed { index, ints ->
        ints.forEachIndexed { index2, i ->
            template_map[index][index2] += i
        }
    }
}

private fun makeUpHistory(startNode: Node, direNode: Node, windDire: Array<Node>): Array<IntArray> {
    val visited = Array(r) { BooleanArray(c) }
    val upHistory = Array(r) { IntArray(c) }
    val dq = ArrayDeque<Wind>()
    dq.add(Wind(startNode.row, startNode.col, 5, false))
    visited[startNode.row][startNode.col] = true
    while (dq.isNotEmpty()) {
        val now = dq.removeFirst()
        if (now.template == 0) continue
        if (!now.isSide) {
            upHistory[now.row][now.col] = now.template
            for (wind in windDire) {
                val nr = now.row + wind.row
                val nc = now.col + wind.col
                if (nr < 0 || nc < 0 || nr >= r || nc >= c || visited[nr][nc]) continue
                if (block_map[now.row][now.col].contains(Node(nr, nc))) continue
                dq.addLast(Wind(nr, nc, now.template, true))
            }
        }
        val nr = now.row + direNode.row
        val nc = now.col + direNode.col
        if (nr < 0 || nc < 0 || nr >= r || nc >= c || visited[nr][nc]) continue
        if (block_map[now.row][now.col].contains(Node(nr, nc))) continue
        dq.addLast(Wind(nr, nc, now.template - 1, false))
        visited[nr][nc] = true
    }
    return upHistory
}

data class Node(val row: Int, val col: Int)
data class Wind(val row: Int, val col: Int, val template: Int, val isSide: Boolean)