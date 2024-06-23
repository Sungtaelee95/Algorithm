import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())

    val n = st.nextToken().toInt() // 현재 휴게소 개수
    val m = st.nextToken().toInt() // 지으려는 갯수
    val l = st.nextToken().toInt() // 고속도로 길이

    val load = mutableListOf<Int>()
    st = StringTokenizer(br.readLine())
    repeat(n) {
        load.add(st.nextToken().toInt())
    }
    load.add(0)
    load.add(l)
    load.sort()

    var s = 1
    var e = l

    while (s <= e) {
        val mid = (s + e) / 2
        var cnt = 0
        for (i in 0 until n + 1) {
            cnt += (load[i + 1] - load[i]-1) / mid
        }
        if (cnt > m) s = mid + 1 else e = mid - 1
    }

    bw.append("$s")

    bw.flush()
    bw.close()
}

data class Info(val start: Int, val end: Int)