import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

var n = 0
var m = 0
var k = 0
var result = Int.MAX_VALUE
lateinit var arr: Array<IntArray>
lateinit var used: BooleanArray
lateinit var inputs: Array<Oper>

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val st = StringTokenizer(br.readLine())
    n = st.nextToken().toInt()
    m = st.nextToken().toInt()
    k = st.nextToken().toInt()
    arr = Array(n) { IntArray(m) }
    repeat(n) { r ->
        arr[r] = br.readLine().split(" ").map { it.toInt() }.toIntArray()
    }
    used = BooleanArray(k)
    inputs = Array(k) { Oper(0, 0, 0) }
    repeat(k) {
        val (r, c, s) = br.readLine().split(" ").map { it.toInt() }
        inputs[it] = Oper(r - 1, c - 1, s)
    }
    for (i in 0 until k) {
        used[i] = true
        val nums = IntArray(k) { Int.MAX_VALUE }
        nums[0] = i
        dfs(1, nums)
        used[i] = false
    }
    bw.append("$result")
    bw.flush()
    bw.close()
}

fun dfs(deep: Int, nums: IntArray) {
    if (deep == k) {
        val temp = Array(n) { IntArray(m) }
        arr.forEachIndexed { r, ints ->
            ints.forEachIndexed { c, i ->
                temp[r][c] = i
            }
        }
        nums.forEach {
            for (opers in inputs[it].s downTo 1) {
                rotation(inputs[it].copy(s = opers), temp)
            }
        }
        result = minOf(result, temp.minOf { it.sum() })
        return
    }
    for (i in 0 until k) {
        if (!used[i]) {
            used[i] = true
            nums[deep] = i
            dfs(deep + 1, nums)
            used[i] = false
        }
    }
}

// 오,아래, 왼, 위
val addDire = arrayOf(Dire(0, 1), Dire(1, 0), Dire(0, -1), Dire(-1, 0))

fun rotation(oper: Oper, ori: Array<IntArray>) {

    val sr = oper.r - oper.s
    val sc = oper.c - oper.s
    val er = oper.r + oper.s
    val ec = oper.c + oper.s
    val dq = ArrayDeque<Node>()
    dq.add(Node(sr, sc, ori[sr + 1][sc]))
    var dire = 0
    while (true) {
        val now = dq.last()
        var nr = now.row + addDire[dire].row
        var nc = now.col + addDire[dire].col
        if (nr !in sr..er || nc !in sc..ec) {
            dire++
            if (dire > 3) break
            nr = now.row + addDire[dire].row
            nc = now.col + addDire[dire].col
        }
        dq.addLast(Node(nr, nc, ori[now.row][now.col]))
    }
    while (dq.isNotEmpty()) {
        val now = dq.removeFirst()
        ori[now.row][now.col] = now.num
    }
}

data class Dire(val row: Int, val col: Int)
data class Node(val row: Int, val col: Int, val num: Int)
data class Oper(val r: Int, val c: Int, val s: Int)