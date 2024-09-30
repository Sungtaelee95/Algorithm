import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

lateinit var map: Array<IntArray>
var n = 0

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    n = readLine().toInt()
    map = Array(n + 1) { IntArray(n + 1) }
    repeat(n) {
        val st = StringTokenizer(readLine())
        val arr = IntArray(n + 1)
        repeat(n) {
            arr[it + 1] = st.nextToken().toInt()
        }
        map[it + 1] = arr
    }
    var result = Int.MAX_VALUE
    for (x in 1..n) {
        for (y in 1..n) {
            for (d1 in 1..n) {
                for (d2 in 1..n) {
                    if (x in 1..x + d1 + d2 &&
                        x + d1 + d2 in x + 1..n &&
                        y - d1 in 1 until y &&
                        y in y - d1 + 1 until y + d2 &&
                        y + d2 in y + 1..n
                    ) {
                        val boundaryMap = gridBoundary(x, y, d1, d2)
                        val cntArr = IntArray(5)
                        for (r in 1 .. n) {
                            for (c in 1 .. n) {
                                cntArr[boundaryMap[r][c]-1] += map[r][c]
                            }
                        }
                        result = minOf(result, cntArr.max() - cntArr.min())
                    }
                }
            }
        }
    }

    bw.append("$result")

    bw.flush()
    bw.close()
}

fun gridBoundary(x: Int, y: Int, d1: Int, d2: Int): Array<IntArray> {
    val gridMap = Array(n + 1) { IntArray(n + 1) }
    // 첫 번째
    for (i in 0..d1) {
        // 첫 번째
        if (x + i in 1..n && y - i in 1..n) gridMap[x + i][y - i] = 5
        // 네 번째
        if (x + d2 + i in 1..n && y + d2 - i in 1..n) gridMap[x + d2 + i][y + d2 - i] = 5
    }
    // 두 번째
    for (i in 0..d2) {
        // 두 번째
        if (x + i in 1..n && y + i in 1..n) gridMap[x + i][y + i] = 5
        // 세 번 째
        if (x + d1 + i in 1..n && y - d1 + i in 1..n) gridMap[x + d1 + i][y - d1 + i] = 5
    }

    // 1번 선거구
    for (r in 1 until x + d1) {
        for (c in 1..y) {
            if (gridMap[r][c] == 0) gridMap[r][c] = 1
        }
    }
    // 2번 선거구
    for (r in 1..x + d2) {
        for (c in y + 1..n) {
            if (gridMap[r][c] == 0) gridMap[r][c] = 2
        }
    }
    // 3번 선거구
    for (r in x + d1..n) {
        for (c in 1 until y - d1 + d2) {
            if (gridMap[r][c] == 0) gridMap[r][c] = 3
        }
    }
    // 4번 선거구
    for (r in x + d2 + 1..n) {
        for (c in y - d1 + d2..n) {
            if (gridMap[r][c] == 0) gridMap[r][c] = 4
        }
    }
    // 5번 선거구 채우기
    for (r in 1..n) {
        var flag = false
        if (gridMap[r].count { it == 5 } == 2){
            for (c in 1..n) {
                if (gridMap[r][c] == 5) {
                    flag = !flag
                }
                if (flag) gridMap[r][c] = 5
            }
        }
    }

    return gridMap
}
