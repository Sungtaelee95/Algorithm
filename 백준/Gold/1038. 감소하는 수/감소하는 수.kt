import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.min

var results = mutableListOf<Long>()
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()

    for (i in 9 downTo 0) {
        dfs("$i".toLong())
        results.add("$i".toLong())
    }

    results.sort()

    if (n >= results.size) {
        bw.append("-1")
    } else {
        bw.append("${results[n]}")
    }

    bw.flush()
    bw.close()
}


fun dfs(num: Long) {
    val lastNum = num.toString().last()-'0'
    for (i in 0 .. 9) {
        if (lastNum > i) {
            results.add(num*10+i)
            dfs(num*10+i)
        }
    }
}