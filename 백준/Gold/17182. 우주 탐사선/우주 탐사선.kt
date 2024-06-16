import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

lateinit var input: Array<IntArray>
lateinit var check: BooleanArray
var result = Int.MAX_VALUE
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())

    val n = st.nextToken().toInt()
    val k = st.nextToken().toInt()

    input = Array(n) { IntArray(n) }
    repeat(n) { row ->
        st = StringTokenizer(br.readLine())
        repeat(n) { col ->
            input[row][col] = st.nextToken().toInt()
        }
    }

    for (t in 0 until n) {
        for (i in 0 until n) {
            for (j in 0 until n) {
                input[i][j] = minOf(input[i][j], input[i][t] + input[t][j])
            }
        }
    }
    check = BooleanArray(n)
    check[k] = true
    dfs(k, 1, 0)

    bw.append("$result")

    bw.flush()
    bw.close()
}

fun dfs(now: Int, deep: Int, dist: Int) {
    if (deep == input.size) {
        result = minOf(result, dist)
        return
    }
    for (i in 0 until input[now].size) {
        if (check[i]) continue
        check[i] = true
        dfs(i, deep+1, dist + input[now][i])
        check[i] = false
    }
}

