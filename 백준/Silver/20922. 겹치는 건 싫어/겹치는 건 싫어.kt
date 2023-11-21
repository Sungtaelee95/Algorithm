import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val K = st.nextToken().toInt()
    st = StringTokenizer(br.readLine())
    val arr = IntArray(N) { st.nextToken().toInt() }

    var start = 0
    var end = 0
    var answer = 0
    val countList = IntArray(100_001) { 0 }

    while (end < N) {
        countList[arr[end]]++
        while (countList[arr[end]] > K) {
            countList[arr[start]]--
            start++
        }
        answer = Math.max(answer, (end-start+1))
        end++
    }

    bw.append("$answer")

    bw.flush()
    bw.close()
}