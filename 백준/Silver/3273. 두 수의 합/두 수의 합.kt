import java.io.*
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val n = br.readLine().toInt()
    val st = StringTokenizer(br.readLine())
    val arr = IntArray(n) { st.nextToken().toInt() }
    val x = br.readLine().toInt()
    arr.sort()
    var answer = 0
    var s = 0
    var e = n-1
    while (s < e) {
        if (arr[s] + arr[e] == x) {
            s++
            answer++
            continue
        }
        if (arr[s] + arr[e] < x) {
            s++
            continue
        }
        if (arr[s] + arr[e] > x) {
            e--
        }
    }
    bw.append("$answer")
    bw.flush()
    bw.close()
}