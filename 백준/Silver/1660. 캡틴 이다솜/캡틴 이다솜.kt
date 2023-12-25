import java.util.*

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    var st = StringTokenizer(br.readLine())

    val N = st.nextToken().toInt()

    val triple = IntArray(122)
    val four = IntArray(122)
    triple[1] = 1
    four[1] = 1

    for (i in 2..121) {
        triple[i] = triple[i - 1] + i
        four[i] = triple[i] + four[i - 1]
    }

    var result = IntArray(N + 1) { Int.MAX_VALUE }
    result[0] = 0
    result[1] = 1

    for (i in 2..N) {
        for (j in 1..121) {
            if (four[j] > i) break
            result[i] = Math.min(result[i], result[i - four[j]] + 1)
        }
    }

    bw.append("${result[N]}")

    bw.flush()
    bw.close()

}