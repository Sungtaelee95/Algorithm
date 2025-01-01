import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.cos
import kotlin.math.max

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val loads = Array(n) { mutableListOf<City>() }
    repeat(m) {
        val (a, b, d) = br.readLine().split(" ").map { it.toInt() }
        loads[a - 1].add(City(b - 1, d))
        loads[b - 1].add(City(a - 1, d))
    }

    val result = prim(n, loads)

    bw.append("$result")

    bw.flush()
    bw.close()
}

private fun prim(
    n: Int,
    loads: Array<MutableList<City>>
): Int {
    val visited = BooleanArray(n)
    val pq = PriorityQueue<City> { o1, o2 -> o1.cost.compareTo(o2.cost) }
    pq.add(City(0,0))

    var totalCost = 0
    var maxEdgeCost = 0

    while (pq.isNotEmpty()) {
        val now = pq.poll()
        if (!visited[now.num]) {
            visited[now.num] = true
            totalCost += now.cost
            maxEdgeCost = maxOf(maxEdgeCost, now.cost)
            for (load in loads[now.num]) {
                if (!visited[load.num]) pq.add(load)
            }
        }
    }
    return totalCost - maxEdgeCost
}

data class City(val num: Int, val cost: Int)