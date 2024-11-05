import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayDeque

var totalResult = 0
val visited = BooleanArray(9)
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val inning = br.readLine().toInt()

    val inningResults = Array(inning) {
        val st = StringTokenizer(br.readLine())
        IntArray(9) { st.nextToken().toInt() }
    }
    val initArray = IntArray(9)
    initArray[3] = 0
    visited[0] = true
    simulate(initArray, inningResults, 0)
    bw.append("$totalResult")
    bw.flush()
    bw.close()
}

var lastOrder = 0 // 마지막에 친게 몇 번째 선수인지

fun simulate(orders: IntArray, inningResults: Array<IntArray>, deep: Int) {
    if (deep == 9) {
        var score = 0
        lastOrder = 0
        inningResults.forEach {
            score += inningSimulate(orders, it)
        }
        totalResult = maxOf(totalResult, score)
        return
    }
    if (deep == 3) {
        simulate(orders, inningResults, deep + 1)
        return
    }
    for (i in 0..8) {
        if (visited[i]) continue
        orders[deep] = i
        visited[i] = true
        simulate(orders, inningResults, deep + 1)
        visited[i] = false
    }
}
fun inningSimulate(orders: IntArray, inningResult: IntArray): Int {
    var score = 0
    var outCount = 0
    val baseDq = ArrayDeque<Boolean>()
    val base = BooleanArray(3)
    while (outCount < 3) {
        if (lastOrder == 9) lastOrder = 0
        if (inningResult[orders[lastOrder]] == 0) {
            outCount++
            lastOrder++
            continue
        }
        if (inningResult[orders[lastOrder]] == 4) score++
        for (i in 2 downTo 0) {
            if (base[i]) {
                if (i + inningResult[orders[lastOrder]] > 2) {
                    base[i] = false
                    score++
                    continue
                }
                base[i + inningResult[orders[lastOrder]]] = true
                base[i] = false
            }
        }
        if (inningResult[orders[lastOrder]] != 4 ) {
            base[inningResult[orders[lastOrder]]-1] = true
        }
        lastOrder++
    }
    return score
}