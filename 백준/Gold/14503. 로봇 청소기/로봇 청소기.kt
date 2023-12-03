import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()

    val map = Array(N) { IntArray(M) { 0 } }

    val nextArr = arrayOf( // 북, 동, 남, 서
        Node(-1, 0),
        Node(0, 1),
        Node(1, 0),
        Node(0, -1)
    )
    st = StringTokenizer(br.readLine())
    val start = Node(st.nextToken().toInt(), st.nextToken().toInt())
    var next = st.nextToken().toInt()
    repeat(N) {
        st = StringTokenizer(br.readLine())
        map[it] = IntArray(M) {st.nextToken().toInt()}
    }
    var result = 0
    val dq = ArrayDeque<Node>()
    dq.add(start)

    while (!dq.isEmpty()) {
        val now = dq.pollFirst()
        if (map[now.row][now.col] == 0) {
            result++
            map[now.row][now.col] = -1
        }
        var cleanCheck = false
        for (i in 0 .. 3) {
            val nextRow = now.row + nextArr[i].row
            val nextCol = now.col + nextArr[i].col
            if (nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= M) continue
            if (map[nextRow][nextCol] == 0) {
                cleanCheck = true
            }
        }
        if (cleanCheck == false) {
            val backPosition = Node (
                now.row + nextArr[getBackPosition(next)].row,
                now.col + nextArr[getBackPosition(next)].col
            )
            if (map[backPosition.row][backPosition.col] == 1) break
            dq.addLast(backPosition)
            continue
        }

        for(i in 1 .. 4) {
            next = getNextDirection(next)
            val nextRow = now.row + nextArr[next].row
            val nextCol = now.col + nextArr[next].col
            if (nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= M) continue
            if (map[nextRow][nextCol] == 0 ) {
                dq.addLast(Node(nextRow, nextCol))
                break
            }
        }
    }

    bw.append("$result")

    bw.flush()
    bw.close()
}

fun getNextDirection(value: Int) : Int {
    if(value - 1 < 0) return 3
    return value - 1
}

fun getBackPosition(direction: Int): Int {
    if (direction - 2 < 0) return direction + 2
    return direction - 2
}

data class Node(val row: Int, val col: Int)

