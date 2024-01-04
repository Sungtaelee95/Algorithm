import java.util.*

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    var st = StringTokenizer(br.readLine())

    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()

    var start = 1
    var end = 0
    var arr = IntArray(M)

    repeat(M) {
        val num = br.readLine().toInt()
        arr[it] = num
        end = Math.max(end, num)
    }


    var result = Int.MAX_VALUE

    while (true) {
        val mid = (start + end) / 2
        var countChild = 0 // 나누어준 아이들 수
        for (i in arr.indices) {
            countChild += if (arr[i] % mid == 0) arr[i] / mid else (arr[i] / mid) + 1
        }
        if (countChild > N) {
            start = mid + 1
        }
        if (countChild <= N) {
            end = mid
            result = Math.min(result, mid)
        }
        if (start == end) break
    }

    bw.append("$result")

    bw.flush()
    bw.close()

}