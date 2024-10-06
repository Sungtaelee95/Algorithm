import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*


val normalArr = intArrayOf(
    0, 2, 4, 6, 8, 10,
    12, 14, 16, 18, 20,
    22, 24, 26, 28, 30,
    32, 34, 36, 38, 40
)
val normalVisited = BooleanArray(21)

val hiddenArr = arrayOf(
    intArrayOf(10, 13, 16, 19),
    intArrayOf(20, 22, 24),
    intArrayOf(30, 28, 27, 26),
    intArrayOf(25, 30, 35, 40)
)
val hiddenVisited = Array(4) { BooleanArray(4) }

lateinit var movePoint: IntArray
val pieces = Array(4) { Piece(0, false, 0, 0) }
var result = 0
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val st = StringTokenizer(readLine())
    movePoint = IntArray(10) { st.nextToken().toInt() }
    simulate(0, 0)

    bw.append("$result")
    bw.flush()
    bw.close()
}

fun simulate(deep: Int, score: Int) {

    result = maxOf(result, score)
    if (deep == 10) return

    for (i in 0..3) {
        val ori = pieces[i].copy()
        if (!pieces[i].isHidden) { // 지름길 안빠짐.
            val nextDist = movePoint[deep] + pieces[i].dist
            if (nextDist >= 21) continue // 결승선을 넘어갔을 경우
            normalVisited[pieces[i].dist] = false // 현재 자리 비워주기
            when (nextDist) {
                5 -> { // 블루 10
                    if (!hiddenVisited[0][0]) {
                        hiddenVisited[0][0] = true
                        pieces[i].isHidden = true
                        pieces[i].row = 0
                        pieces[i].col = 0
                        simulate(deep + 1, score + hiddenArr[0][0])
                        hiddenVisited[0][0] = false
                    }
                }

                10 -> { // 블루 20
                    if (!hiddenVisited[1][0]) {
                        hiddenVisited[1][0] = true
                        pieces[i].isHidden = true
                        pieces[i].row = 1
                        pieces[i].col = 0
                        simulate(deep + 1, score + hiddenArr[1][0])
                        hiddenVisited[1][0] = false
                    }
                }

                15 -> { // 블루 30
                    if (!hiddenVisited[2][0]) {
                        hiddenVisited[2][0] = true
                        pieces[i].isHidden = true
                        pieces[i].row = 2
                        pieces[i].col = 0
                        simulate(deep + 1, score + hiddenArr[2][0])
                        hiddenVisited[2][0] = false
                    }
                }

                20 -> { // 검정 40이나 2점에서 모이기 때문에 추가 작성
                    if (!hiddenVisited[3][3]) {
                        hiddenVisited[3][3] = true
                        pieces[i].isHidden = true
                        pieces[i].row = 3
                        pieces[i].col = 3
                        simulate(deep + 1, score + hiddenArr[3][3])
                        hiddenVisited[3][3] = false
                    }
                }

                else -> {
                    if (!normalVisited[nextDist]) { // 다음 방향에 말이 있는지 확인.
                        normalVisited[nextDist] = true
                        pieces[i].dist = nextDist
                        simulate(deep + 1, score + normalArr[nextDist])
                        normalVisited[nextDist] = false
                    }
                }
            }
            normalVisited[ori.dist] = true
        } else { // 지름길 빠짐.
            var nr = pieces[i].row
            var nc = pieces[i].col
            
            hiddenVisited[pieces[i].row][pieces[i].col] = false
            if (nr < 4) {
                for (j in 1..movePoint[deep]) {
                    nc++
                    if (hiddenArr[nr].size <= nc) {
                        if (nr < 3) {
                            nr = 3
                            nc = 0
                        } else {
                            nr = 4
                            nc = 0
                            break
                        }
                    }
                }
                if (nr < 4) {
                    if (!hiddenVisited[nr][nc]) {
                        hiddenVisited[nr][nc] = true
                        pieces[i].row = nr
                        pieces[i].col = nc
                        simulate(deep + 1, score + hiddenArr[nr][nc])
                        hiddenVisited[nr][nc] = false
                    }
                } else {
                    simulate(deep + 1, score)
                }
            }
            hiddenVisited[ori.row][ori.col] = true
        }
        pieces[i] = ori // 복구
    }
}

data class Piece(var dist: Int, var isHidden: Boolean, var row: Int, var col: Int)