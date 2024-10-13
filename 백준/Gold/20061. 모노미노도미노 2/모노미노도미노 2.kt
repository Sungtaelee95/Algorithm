import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

val blueBoard = Array(4) { BooleanArray(6) }
val greenBoard = Array(6) { BooleanArray(4) }
var totalScore = 0
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val n = readLine().toInt()

    repeat(n) {
        val st = StringTokenizer(readLine())
        addBlock(st.nextToken().toInt(), st.nextToken().toInt(), st.nextToken().toInt())
        equalLineClear()
        topLineClear()
    }
    var blockCount = 0
    blueBoard.forEach {
        blockCount += it.count { it }
    }
    greenBoard.forEach {
        blockCount += it.count { it }
    }

    bw.append("$totalScore\n$blockCount")

    bw.flush()
    bw.close()
}

fun topLineClear() {
    while (true) {
        var flag = true
        for (row in 0..3) {
            for (col in 0..1) {
                if (blueBoard[row][col]) {
                    flag = false
                    leftBlue(5, false)
                }
            }
        }
        if (flag) break
    }
    while (greenBoard[1].any { it } ) {
        downGreen(5, false)
    }
}

fun leftBlue(col: Int, isScore: Boolean) {
    if (isScore) totalScore++
    for (nc in col downTo 1) {
        for (row in 0..3) {
            blueBoard[row][nc] = blueBoard[row][nc - 1]
        }
    }

    blueBoard.forEach {
        it[0] = false
    }
}

fun downGreen(row: Int, isScore: Boolean) {
    if (isScore) totalScore++
    for (col in 0..3) {
        for (nr in row downTo 1) {
            greenBoard[nr][col] = greenBoard[nr - 1][col]
        }
    }
    greenBoard[0] = BooleanArray(4)
}

fun equalLineClear() {
    // 블루
    while (true) {
        var stop = true
        for (col in 5 downTo 2) {
            var flag = true
            for (row in 0..3) {
                // 안채워진 부분이 있다면 break
                if (!blueBoard[row][col]) flag = false
            }
            if (flag) {
                leftBlue(col, true)
                stop = false
                break
            }
        }
        if (stop) break
    }
    // 그린
    while (true) {
        var flag = true
        for (row in 5 downTo 0) {
            if (greenBoard[row].all { it }) {
                downGreen(row, true)
                flag = false
                break
            }
        }
        if (flag) break
    }
}

fun addBlock(t: Int, r: Int, c: Int) {
    var findrow = 0
    var findcol = 0
    when (t) {
        // ㅁ
        1 -> {
            for (i in 0..5) {
                if (blueBoard[r][i]) break
                findcol = i
            }
            blueBoard[r][findcol] = true
            for (i in 0..5) {
                if (greenBoard[i][c]) break
                findrow = i
            }
            greenBoard[findrow][c] = true
        }
        //앞 기준
        //ㅁㅁ
        2 -> {
            for (i in 0..4) {
                if (blueBoard[r][i] || blueBoard[r][i + 1]) break
                findcol = i
            }
            blueBoard[r][findcol] = true
            blueBoard[r][findcol + 1] = true
            for (i in 0..5) {
                if (greenBoard[i][c] || greenBoard[i][c + 1]) break
                findrow = i
            }
            greenBoard[findrow][c] = true
            greenBoard[findrow][c + 1] = true
        }
        // ㅁ 기준
        // ㅁ
        3 -> {
            for (i in 0..5) {
                if (blueBoard[r][i] || blueBoard[r + 1][i]) break
                findcol = i
            }
            blueBoard[r][findcol] = true
            blueBoard[r + 1][findcol] = true

            for (i in 0..4) {
                if (greenBoard[i][c] || greenBoard[i + 1][c]) break
                findrow = i
            }
            greenBoard[findrow][c] = true
            greenBoard[findrow + 1][c] = true
        }
    }
}