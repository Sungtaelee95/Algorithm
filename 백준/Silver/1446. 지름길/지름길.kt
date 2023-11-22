import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val D = st.nextToken().toInt()

    var loadInfo = mutableListOf<Node>()
    repeat(N) {
        st = StringTokenizer(br.readLine())
        val start = st.nextToken().toInt()
        val next = st.nextToken().toInt()
        val dist = st.nextToken().toInt()
        loadInfo.add(Node(start, next, dist))
    }

    val distInfo = IntArray(10_001) { it }

    for (node in loadInfo.sortedBy { it.start }) {
        distInfo[node.next] = Math.min(distInfo[node.next], distInfo[node.start] + node.dist)
        for (i in node.next until distInfo.size - 1) {
            distInfo[i + 1] = Math.min(distInfo[i + 1], distInfo[i] + 1)
            if(i > D) break
        }
    }

    bw.append("${distInfo[D]}")

    bw.flush()
    bw.close()
}

data class Node(val start: Int, val next: Int, val dist: Int)