import java.io.BufferedReader // ktlint-disable filename
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())

    val N = st.nextToken().toInt() // 학생 수
    val M = st.nextToken().toInt() // 도로정보의 개수
    val X = st.nextToken().toInt() // 목표 지점

    val nodeList = Array(M + 1) { mutableListOf<Node>() }
    val timeList = Array(M + 1) { intArrayOf() }

    repeat(M) {
        st = StringTokenizer(br.readLine())
        val start = st.nextToken().toInt()
        nodeList[start].add(Node(st.nextToken().toInt(), st.nextToken().toInt()))
    }

    for (std in 1..N) {
        var distArr = IntArray(N + 1) { Int.MAX_VALUE }
        val dq = ArrayDeque<Node>()
        for (node in nodeList[std]) {
            distArr[node.next] = node.time
            dq.addLast(node)
        }
        while (!dq.isEmpty()) {
            val node = dq.pollFirst()
            for (info in nodeList[node.next]) {
                if (distArr[info.next] > node.time + info.time) {
                    distArr[info.next] = node.time + info.time
                    val newNode = Node(info.next, info.time + node.time)
                    dq.addLast(newNode)
                }
            }
        }
        timeList[std] = distArr
    }

    var maxTime = 0
    for (i in 1..N) {
        if(X == i) continue
        maxTime = Math.max(maxTime, timeList[X][i] + timeList[i][X])
    }
    bw.append("$maxTime")

    bw.flush()
    bw.close()
}

data class Node(val next: Int, val time: Int)
