import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*


fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val n = br.readLine().toInt()
    val st = StringTokenizer(br.readLine())
    val arr = IntArray(n) { st.nextToken().toInt() }
    val result = IntArray(n)
    result[0] = arr[0]
    var resultIndex = 1
    for (i in 1 until n) {
        if (result[resultIndex-1] < arr[i]){
            resultIndex++
            result[resultIndex-1] = arr[i]
            continue
        }
        var s = 0
        var e = resultIndex
        while (s <= e) {
            val mid = (s+e)/2
            if (result[mid] < arr[i]) {
                s = mid+1
            } else {
                e = mid-1
            }
        }
        result[s] = arr[i]
    }

    bw.append("$resultIndex")

    bw.flush()
    bw.close()
}