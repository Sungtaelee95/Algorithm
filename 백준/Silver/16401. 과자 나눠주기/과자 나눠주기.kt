import java.util.*

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    var st = StringTokenizer(br.readLine())

    val M = st.nextToken().toInt() // 조카 수
    val N = st.nextToken().toInt() // 과자 수

    st = StringTokenizer(br.readLine())
    val arr = LongArray(N) { st.nextToken().toLong() }

    var start = 1L
    var end = arr.max()

    var result = 0L
    var mid = (start + end) / 2L

    while (start <= end) {
        var cookieCount = 0L
        for (len in arr) {
            cookieCount += len / mid
        }
        if (cookieCount >= M) {
            start = mid + 1
            result = mid
        }
        if (cookieCount < M) {
            end = mid - 1
        }
        mid = (start + end) / 2L
    }

    bw.appendLine("$result")

    bw.flush()
    bw.close()
}

