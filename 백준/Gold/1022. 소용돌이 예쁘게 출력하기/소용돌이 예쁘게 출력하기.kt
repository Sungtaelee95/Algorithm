import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.abs

lateinit var map: Array<IntArray>
lateinit var p1: Node
lateinit var p2: Node

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val st = StringTokenizer(br.readLine())

    p1 = Node(st.nextToken().toInt(), st.nextToken().toInt())
    p2 = Node(st.nextToken().toInt(), st.nextToken().toInt())

    map = Array(p2.row - p1.row + 1) { IntArray(p2.col - p1.col + 1) }
    turned(maxOf(maxOf(abs(p1.col), abs(p2.col)), maxOf(abs(p1.row), abs(p2.row))) + 1)

    var length = 0
    map.forEach {
        it.forEach {
            length = maxOf("$it".length, length)
        }
    }

    for (row in map.indices) {
        for (col in map[0].indices) {
            bw.append(String.format("%${length}d ", map[row][col]))
        }
        bw.appendLine()
    }

    bw.flush()
    bw.close()
}

fun turned(depth: Int) {
    var num = ((depth * 2) + 1) * ((depth * 2) + 1)
    val cnt = 2 * depth
    val node = Node(depth, depth)
    if (isPrintNum(node)) map[depth - p1.row][depth - p1.col] = num
    for (i in 1..cnt) {
        node.col--
        num--
        if (isPrintNum(node)) map[node.row - p1.row][node.col - p1.col] = num
    }
    for (i in 1..cnt) {
        node.row--
        num--
        if (isPrintNum(node)) map[node.row - p1.row][node.col - p1.col] = num
    }
    for (i in 1..cnt) {
        node.col++
        num--
        if (isPrintNum(node)) map[node.row - p1.row][node.col - p1.col] = num
    }
    for (i in 1 until cnt) {
        node.row++
        num--
        if (isPrintNum(node)) map[node.row - p1.row][node.col - p1.col] = num
    }
    if (depth > 0) {
        turned(depth - 1)
    }
}

fun isPrintNum(node: Node): Boolean =
    node.row in p1.row..p2.row &&
            node.col in p1.col..p2.col


data class Node(var row: Int, var col: Int)

