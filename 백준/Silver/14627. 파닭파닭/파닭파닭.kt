import java.util.*

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    var st = StringTokenizer(br.readLine())

    val S = st.nextToken().toInt() // 파의 개수
    val C = st.nextToken().toInt() // 파닭의 수

    val arr = IntArray(S)
    var end = 0L
    var sum = 0L
    repeat(S) {
        arr[it] = br.readLine().toInt()
        sum += arr[it]
        end = Math.max(end, arr[it].toLong())
    }

    var start = 1L
    var result = Long.MAX_VALUE
    var paCount = 0L
    var mid = (start + end) / 2L // 파 길이
    while (start <= end) {
        paCount = 0L
        for (i in 0 until arr.size) {
            repeat(arr[i] / mid.toInt()){
                if (paCount < C) paCount++
            }
        }
        if (paCount == C.toLong()) {
            start = mid + 1
            result = Math.min(result, sum - paCount * mid)
        } else if (paCount < C) {
            end = mid - 1
        }
        mid = (start + end) / 2L
    }

    bw.appendLine("$result")

    bw.flush()
    bw.close()
}

