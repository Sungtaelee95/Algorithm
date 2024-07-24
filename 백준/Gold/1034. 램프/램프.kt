import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val st = StringTokenizer(br.readLine())
    var n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    val map = mutableMapOf<String, Int>()

    repeat(n) {
        val input = br.readLine()
        if (map.containsKey(input)) {
            map[input] = map[input]!! + 1
        } else {
            map[input] = 1
        }
    }

    val count = br.readLine().toInt()
    var result = 0
    map.forEach {
        val pattenCnt = it.value
        var zero = 0
        it.key.forEach {
            if (it == '0') zero++
        }
        if (zero <= count && count % 2 == zero % 2) {
            result = maxOf(result, pattenCnt)
        }
    }

    bw.append("$result")

    bw.flush()
    bw.close()
}