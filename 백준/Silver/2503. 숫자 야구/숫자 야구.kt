import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

private var N = 0
private var cnt = 0
private val num = BooleanArray(988)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    N = st.nextToken().toInt()
    for (n in 0 until N) {
        st = StringTokenizer(br.readLine())
        check(st.nextToken().toInt(), st.nextToken().toInt(), st.nextToken().toInt())
    }
    for (i in 123..987) if (!num[i]) cnt++
    bw.append("$cnt")
    
    bw.flush()
    bw.close()
}

fun check(number: Int, strike: Int, ball: Int) {
    val a = number / 100
    val b = number % 100 / 10
    val c = number % 100 % 10
    for (i in 123..987) {
        if (num[i]) continue
        val a2 = i / 100
        val b2 = i % 100 / 10
        val c2 = i % 100 % 10
        var strike2 = 0
        var ball2 = 0
        if (a2 == 0 || b2 == 0 || c2 == 0 || a2 == b2 || b2 == c2 || a2 == c2) {
            num[i] = true
            continue
        }
        if (a == a2) strike2++
        if (b == b2) strike2++
        if (c == c2) strike2++
        if (a == b2 || a == c2) ball2++
        if (b == a2 || b == c2) ball2++
        if (c == b2 || c == a2) ball2++
        if (strike != strike2 || ball != ball2) num[i] = true
    }
}

