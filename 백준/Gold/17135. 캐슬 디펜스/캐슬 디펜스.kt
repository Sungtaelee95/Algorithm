import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.abs

var N = 0
var M = 0
var D = 0

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    var st = StringTokenizer(br.readLine())
    N = st.nextToken().toInt()
    M = st.nextToken().toInt()
    D = st.nextToken().toInt()

    val board = Array(N) { intArrayOf() }
    repeat(N) {
        st = StringTokenizer(br.readLine())
        board[it] = IntArray(M) { st.nextToken().toInt() }
    }

    var result = 0

    for (i in 0 until M - 2) {
        for (j in i + 1 until M - 1) {
            for (k in j + 1 until M) {
                val attack = IntArray(M) { 0 }
                attack[i] = 1
                attack[j] = 1
                attack[k] = 1
                val tempBoard = Array(N) { IntArray(M) }
                for (row in 0 until N) {
                    for (col in 0 until board[row].size) {
                        tempBoard[row][col] = board[row][col]
                    }
                }
                result = Math.max(result, simulation(tempBoard + attack))
            }
        }
    }

    bw.append("$result")

    bw.flush()
    bw.close()
}

fun simulation(board: Array<IntArray>): Int {
    var brokenCount = 0
    while (true) {
        val totalAttack = ArrayDeque<Pair<Int, Int>>()
        for (attack in 0 until M) { // 공격 포지션 찾기
            val attackPq = PriorityQueue<Node>(kotlin.Comparator { o1, o2 ->
                if (o1.dist == o2.dist) o1.col.compareTo(o2.col) else o1.dist.compareTo(o2.dist)
            })
            if (board[N][attack] == 1) { // (N, attack), (row, col)
                for (row in 0 until N) {
                    for (col in 0 until M) {
                        if (board[row][col] == 1 && abs(N - row) + abs(attack - col) <= D) {
                            attackPq.add(Node(row, col, abs(N - row) + abs(attack - col)))
                        }
                    }
                }
            }
            if (attackPq.isNotEmpty()) {
                val attackPosition = attackPq.poll()
                totalAttack.add(Pair(attackPosition.row, attackPosition.col))
            }
        }
        while (totalAttack.isNotEmpty()) { // 찾은 공격포지션 중 처음 공격하는 포지션일 경우 카운트 업
            val now = totalAttack.removeFirst()
            if (board[now.first][now.second] == 1) {
                brokenCount++
                board[now.first][now.second] = 0
            }
        }

        for (i in N - 1 downTo 0) { // 적군 전진
            if (i == 0) {
                board[i] = IntArray(M) { 0 }
                continue
            }
            board[i] = board[i - 1]
        }

        var endCheck = 0
        board.forEachIndexed { index, ints -> // 궁수라인을 제외한 모두 0인지 확인
            if (index != N) endCheck += ints.sum()
        }
        if (endCheck == 0) break
    }
    return brokenCount
}

data class Node(val row: Int, val col: Int, val dist: Int)
