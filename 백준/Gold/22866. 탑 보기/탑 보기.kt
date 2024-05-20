import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.abs

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val n = br.readLine().toInt()
    val st = StringTokenizer(br.readLine())

    val highs = IntArray(n + 1)

    for (i in 1..n) {
        highs[i] = st.nextToken().toInt()
    }

    val count = IntArray(n + 1)
    val dist = IntArray(n + 1) {100_001}

    val left = Stack<Building>()
    // 왼쪽 확인
    for (i in 1..n) {
        val high = highs[i]
        while(left.isNotEmpty() && left.peek().high <= high) {
            left.pop()
        }
        count[i] += left.size
        if (left.isNotEmpty()) dist[i] = left.peek().index
        left.push(Building(i, high))
    }
    // 오른쪽 확인
    val right = Stack<Building>()
    for (i in n downTo 1) {
        val high = highs[i]
        while(right.isNotEmpty() && right.peek().high <= high) {
            right.pop()
        }
        count[i] += right.size
        if (right.isNotEmpty() && abs(i-dist[i]) > right.peek().index-i) dist[i] = right.peek().index
        right.push(Building(i, high))
    }

    for (i in 1 .. n) {
        if (count[i] == 0) {
            bw.appendLine("0")
            continue
        }
        bw.appendLine("${count[i]} ${dist[i]}")
    }
    bw.flush()
    bw.close()
}

data class Building(val index: Int, val high: Int)



