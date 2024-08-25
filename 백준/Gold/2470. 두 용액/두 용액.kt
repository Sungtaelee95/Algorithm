import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.abs


fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    st = StringTokenizer(br.readLine())
    val list = List(n) { st.nextToken().toInt() }.sorted()

    var start = 0
    var end = n-1
    var min = Int.MAX_VALUE

    var resultMin = 0
    var resultMax = 0

    while (start < end) {
        val num = list[start] + list[end]
        if (abs(num) < min){
            min = abs(num)
            resultMin = list[start]
            resultMax = list[end]
        }
        if (num <= 0) {
            start++
        } else {
            end--
        }
    }

    bw.append("$resultMin $resultMax")

    bw.flush()
    bw.close()
}

