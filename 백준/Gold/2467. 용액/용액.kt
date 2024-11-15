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
    val arr = LongArray(n) { st.nextToken().toLong() }
    var left = 0
    var right = n - 1
    var result = Long.MAX_VALUE
    var resultR = right
    var resultL = 0
    while (left != right) {
        val sum = abs(arr[left] + arr[right])
        if (sum < abs(result)) {
            result = arr[left] + arr[right]
            resultR = right
            resultL = left
        }
        when {
            arr[left] + arr[right] < 0 -> left++
            else -> right--
        }
    }

    bw.append("${arr[resultL]} ${arr[resultR]}")

    bw.flush()
    bw.close()
}

