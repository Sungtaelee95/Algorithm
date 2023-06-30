import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.text.FieldPosition
import java.util.ArrayDeque
import java.util.ArrayList
import java.util.LinkedList
import java.util.Stack
import java.util.StringTokenizer

data class Info(val row: Int, val col: Int)
fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val rowArr = intArrayOf(0, 0, -1, 1)
    val colArr = intArrayOf(-1, 1, 0, 0)

    val N = br.readLine().toInt()
    val space = Array(N) {CharArray(N)}
    val nomalMap = Array(N) {IntArray(N)}
    val blindnessMap = Array(N) {IntArray(N)}

    extracted(N, br, space)

    var nomalCount = i(N, nomalMap, space, rowArr, colArr)

    extracted(N, blindnessMap, space, rowArr, colArr, bw, nomalCount)
    

    bw.flush()
    bw.close()

}

private fun i(
    N: Int,
    nomalMap: Array<IntArray>,
    space: Array<CharArray>,
    rowArr: IntArray,
    colArr: IntArray
): Int {
    var nomalCount = 0
    for (row in 0 until N) {
        for (col in 0 until N) {
            if (nomalMap[row][col] != 0) continue
            nomalCount++

            val dq = ArrayDeque<Info>()
            dq.add(Info(row, col))
            nomalMap[row][col] = nomalCount

            val check = space[row][col]

            while (!dq.isEmpty()) {
                val now = dq.pollFirst()
                nomalMap[now.row][now.col] = nomalCount

                for (i in 0..3) {
                    val nextRow = now.row + rowArr[i]
                    val nextCol = now.col + colArr[i]

                    if (nextRow < 0 || nextCol < 0 || nextCol >= N || nextRow >= N) continue
                    if (nomalMap[nextRow][nextCol] != 0) continue
                    if (space[nextRow][nextCol] != check) continue

                    nomalMap[nextRow][nextCol] = nomalCount
                    dq.addLast(Info(nextRow, nextCol))

                }
            }
        }
    }
    return nomalCount
}

private fun extracted(
    N: Int,
    blindnessMap: Array<IntArray>,
    space: Array<CharArray>,
    rowArr: IntArray,
    colArr: IntArray,
    bw: BufferedWriter,
    nomalCount: Int
) {
    var blindnessCount = 0
    for (row in 0 until N) {
        for (col in 0 until N) {
            if (blindnessMap[row][col] != 0) continue
            blindnessCount++

            val dq = ArrayDeque<Info>()
            dq.add(Info(row, col))
            blindnessMap[row][col] = blindnessCount

            val check = if (space[row][col] == 'B') "B" else "RG"

            while (!dq.isEmpty()) {
                val now = dq.pollFirst()
                blindnessMap[now.row][now.col] = blindnessCount

                for (i in 0..3) {
                    val nextRow = now.row + rowArr[i]
                    val nextCol = now.col + colArr[i]

                    if (nextRow < 0 || nextCol < 0 || nextCol >= N || nextRow >= N) continue
                    if (blindnessMap[nextRow][nextCol] != 0) continue
                    if (!check.contains(space[nextRow][nextCol])) continue

                    blindnessMap[nextRow][nextCol] = blindnessCount
                    dq.addLast(Info(nextRow, nextCol))

                }
            }
        }
    }
    bw.append("$nomalCount $blindnessCount")
}

private fun extracted(N: Int, br: BufferedReader, space: Array<CharArray>) {
    for (i in 0 until N) {
        val lineColor = br.readLine()
        for (j in 0 until N) {
            space[i][j] = lineColor[j]
        }
    }
}
