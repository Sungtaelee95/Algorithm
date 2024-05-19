import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*


lateinit var dp: IntArray
lateinit var visit: BooleanArray
lateinit var tree: Array<MutableList<Int>>
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())

    val n = st.nextToken().toInt()
    val r = st.nextToken().toInt()
    val q = st.nextToken().toInt()

    tree = Array(n+1 ){ mutableListOf<Int>() }

    repeat (n-1) {
        st = StringTokenizer(br.readLine())
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        tree[a].add(b)
        tree[b].add(a)
    }

    dp = IntArray(n+1)
    visit = BooleanArray(n+1)

    calc(r)
    repeat(q) {
        bw.appendLine("${dp[br.readLine().toInt()]} ")
    }

    bw.flush()
    bw.close()
}

fun calc(node: Int): Int {
    visit[node] = true
    var count = 1
    if (dp[node] == 0) {
        for (i in tree[node]) {
            if (!visit[i]) {
                visit[i] = true
                count += calc(i)
            }
        }
    }
    dp[node] = count
    return dp[node]
}


