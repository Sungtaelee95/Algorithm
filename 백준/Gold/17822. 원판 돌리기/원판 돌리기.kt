import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayDeque

lateinit var map: Array<IntArray>
val rows = intArrayOf(0, 0, 1, -1)
val cols = intArrayOf(1, -1, 0, 0)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val (n, m, t) = br.readLine().split(" ").map { it.toInt() }
    map = Array(n) { IntArray(m) }
    repeat(n) {
        val st = StringTokenizer(br.readLine())
        map[it] = IntArray(m) { st.nextToken().toInt() }
    }

    repeat(t) {
        val (x, d, k) = br.readLine().split(" ").map { it.toInt() }
        // 회전
        for (i in 0 until map.size) {
            if ((i+1) % x == 0) {
                rotation(i, k , d)
            }
        }
        // 변경 내용 저장할 배열 선언
        val temp = Array(n) { IntArray(m) }
        map.forEachIndexed { row, ints ->
            ints.forEachIndexed { col, i ->
                temp[row][col] = i
            }
        }
        // 인접 제거
        var flag = true
        for (row in 0 until n) {
            for (col in 0 until m) {
                for (i in 0 .. 3){
                    val nr = row + rows[i]
                    if (nr !in 0 until n) continue
                    val nc = if (col + cols[i] < 0) m-1 else if (col + cols[i] >= m) 0 else col + cols[i]
                    if (map[nr][nc] == map[row][col] && map[row][col] != 0) {
                        flag = false
                        temp[nr][nc] = 0
                        temp[row][col]= 0
                    }
                }
            }
        }
        // 제거한 숫자가 없을 경우
        if (flag) {
            var cnt = 0
            var sum = 0
            temp.forEach { arr->
                cnt += arr.count { it > 0 }
                sum += arr.sum()
            }
            val avg = sum.toDouble() / cnt.toDouble()
            for (row in 0 until n) {
                for (col in 0 until m) {
                    if (temp[row][col] > 0) {
                        if (temp[row][col].toDouble() == avg) continue
                        if (temp[row][col].toDouble() > avg) temp[row][col]-- else temp[row][col]++
                    }
                }
            }
        }
        // 갱신
        map = temp
    }
    var result = 0
    map.forEach {
        result += it.sum()
    }
    bw.append("$result")
    bw.flush()
    bw.close()
}

fun rotation(row: Int, dist: Int, way: Int) {
    val dq = ArrayDeque<Int>()
    dq.addAll(map[row].toList())
    // 회전
    if (way == 0) {
        repeat(dist) {
            dq.addFirst(dq.removeLast())
        }
    } else {
        repeat(dist) {
            dq.addLast(dq.removeFirst())
        }
    }
    // 갱신
    map[row] = dq.toIntArray()
}

