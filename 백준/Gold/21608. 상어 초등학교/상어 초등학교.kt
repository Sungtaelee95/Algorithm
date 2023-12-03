import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.abs

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()

    val linkedHashMap = LinkedHashMap<Int, IntArray>()

    repeat(N * N) {
        st = StringTokenizer(br.readLine())
        val stdNum = st.nextToken().toInt()
        val likeStdNum = IntArray(4) { st.nextToken().toInt() }
        linkedHashMap[stdNum] = likeStdNum
    }

    val sitMap = Array(N) { IntArray(N) { 0 } }
    val rowArr = intArrayOf(-1, -1, - 1, 0, 0, 1, 1, 1)
    val colArr = intArrayOf(-1, 0, 1, -1, 1, -1, 0, 1)
    linkedHashMap.forEach {
        val stdNum = it.key
        val likeStdNum = it.value
        val sitList = mutableListOf<Node>()
        for (row in 0 until N) {
            for (col in 0 until N) {
                if (sitMap[row][col] != 0) continue
                var score = 0
                var empty = 0
                for (i in 0 until 8) {
                    val nextRow = row + rowArr[i]
                    val nextCol = col + colArr[i]
                    if (abs(row - nextRow) + abs(col - nextCol) != 1) continue
                    if (nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= N) continue
                    if (sitMap[nextRow][nextCol] == 0) empty++
                    if (likeStdNum.contains(sitMap[nextRow][nextCol])) score++
                }
                sitList.add(Node(row, col, score, empty))
            }
        }
        sitList.sortWith(
            kotlin.Comparator { o1, o2 ->
                if (o1.score == o2.score) {
                    if (o1.empty == o2.empty) {
                        if (o1.row == o2.row) {
                            o1.col.compareTo(o2.col)
                        } else {
                            o1.row.compareTo(o2.row)
                        }
                    } else {
                        o2.empty.compareTo(o1.empty)
                    }
                } else o2.score.compareTo(o1.score)
            }
        )
        sitMap[sitList.first().row][sitList.first().col] = stdNum
    }

    var result = 0

    for (row in 0 until N) {
        for (col in 0 until N) {
            var count = 0
            for (i in 0 until 8) {
                val nextRow = row + rowArr[i]
                val nextCol = col + colArr[i]
                if (abs(row - nextRow) + abs(col - nextCol) != 1) continue
                if (nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= N) continue
                if(linkedHashMap[sitMap[row][col]]!!.contains(sitMap[nextRow][nextCol])) count++
            }
            when(count) {
                0 -> result += 0
                1 -> result += 1
                2 -> result += 10
                3 -> result += 100
                4 -> result += 1000
            }
        }
    }

    bw.append("$result")
    bw.flush()
    bw.close()
}

data class Node(val row: Int, val col: Int, val score: Int, val empty: Int)

