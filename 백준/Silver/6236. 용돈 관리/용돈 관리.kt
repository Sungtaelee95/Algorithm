import java.util.*

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    var st = StringTokenizer(br.readLine())

    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()

    val arr = LongArray(N) { br.readLine().toLong() }

    var start = 1L
    var end = 1_000_000_000L

    var result = 0L

    while (start <= end) {
        var drawCount = 1L
        val mid = (start + end) / 2L
        var money = mid
        var check = false

        for (i in arr.indices) {
            if (mid < arr[i]) {
                check = true
                break
            }

            if (money < arr[i]) {
                drawCount++
                money = mid
            }
            money -= arr[i]
        }
        if (check || drawCount > M) {
            start = mid + 1
            continue
        }
        result = mid
        end = mid - 1
    }

    bw.appendLine("$result")

    bw.flush()
    bw.close()
}

