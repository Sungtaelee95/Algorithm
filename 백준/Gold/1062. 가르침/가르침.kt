import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.min

val visit = BooleanArray(26)
val words = mutableListOf<Set<Char>>()
var result = 0
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val k = st.nextToken().toInt()
    if (k == 26) {
        bw.append("$n")
        bw.flush()
        bw.close()
        return
    }
    if (k < 5) {
        bw.append("0")
        bw.flush()
        bw.close()
        return
    }

    visit['a' - 'a'] = true
    visit['n' - 'a'] = true
    visit['t' - 'a'] = true
    visit['i' - 'a'] = true
    visit['c' - 'a'] = true

    repeat(n) {
        words.add(br.readLine().replace("anta", "").replace("tica", "").toSet())
    }

    dfs(k-5, 0)

    bw.append("$result")

    bw.flush()
    bw.close()
}

fun dfs(k: Int, idx: Int) {

    if (k == 0) {
        var count = 0
        words.forEach {
            var flag = true
            for (char in it) {
                if (!visit[char - 'a']) {
                    flag = false
                    break
                }
            }
            if (flag) count++
        }
        result = maxOf(result, count)
        return
    }

    for (i in idx+1..25) {
        if (!visit[i]) {
            visit[i] = true
            dfs(k-1, i)
            visit[i] = false
        }
    }
}