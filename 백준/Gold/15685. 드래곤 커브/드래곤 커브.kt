import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())

    val n = st.nextToken().toInt()
    val map = Array(101) { BooleanArray(101) }

    // x: col , y: row
    val next = listOf<Node>(Node(0, 1), Node(-1, 0), Node(0, -1), Node(1, 0))

    repeat(n) {
        st = StringTokenizer(br.readLine())
        val col = st.nextToken().toInt()
        val row = st.nextToken().toInt()
        val d = st.nextToken().toInt()
        val g = st.nextToken().toInt()
        val history = mutableListOf<Node>()
        map[row][col] = true
        map[row + next[d].row][col + next[d].col] = true
        history.add(next[d])
        var last = Node(row + next[d].row, col + next[d].col)
        repeat(g) {
            val tempHis = mutableListOf<Node>()
            for (hist in history.reversed()) {
                val nn = (next.indexOf(hist) + 1) % 4
                val temp = Node(last.row + next[nn].row, last.col + next[nn].col)
                map[temp.row][temp.col] = true
                last = temp
                tempHis.add(next[nn])
            }
            history.addAll(tempHis)
        }
    }

    var result = 0
    for (i in 0..99) {
        for (j in 0..99) {
            if (map[i][j] && map[i + 1][j] && map[i + 1][j + 1] && map[i][j + 1]) result++
        }
    }

    bw.append("$result")

    bw.flush()
    bw.close()
}

data class Node(val row: Int, val col: Int)
