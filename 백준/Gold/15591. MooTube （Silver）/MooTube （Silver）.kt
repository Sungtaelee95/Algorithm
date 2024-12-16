import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayDeque

lateinit var arr: Array<MutableList<Node>>
var N = 0

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())

    N = st.nextToken().toInt()
    val Q = st.nextToken().toInt()

    arr = Array(N + 1) { mutableListOf() }

    repeat(N - 1) {
        st = StringTokenizer(br.readLine())
        val p = st.nextToken().toInt()
        val q = st.nextToken().toInt()
        val r = st.nextToken().toInt()
        arr[p].add(Node(q,r))
        arr[q].add(Node(p,r))
    }

    repeat(Q) {
        st = StringTokenizer(br.readLine())
        val k = st.nextToken().toInt()
        val v = st.nextToken().toInt()
        bw.appendLine("${find(k, v)}")
    }

    bw.flush()
    bw.close()
}

fun find(k: Int, number: Int): Int {
    val dq = ArrayDeque<Node>()
    var count = 0
    val visited = BooleanArray(N + 1)
    visited[number] = true
    for (i in arr[number].indices) {
        if (arr[number][i].dist >= k) {
            dq.add(arr[number][i])
            visited[arr[number][i].index] = true
            count++
        }
    }

    while (dq.isNotEmpty()) {
        val now = dq.removeFirst()
        for (i in arr[now.index].indices) {
            if (arr[now.index][i].dist >= k && !visited[arr[now.index][i].index]) {
                dq.addLast(arr[now.index][i])
                visited[arr[now.index][i].index] = true
                count++
            }
        }
    }
    return count
}

data class Node(val index: Int, val dist: Int)