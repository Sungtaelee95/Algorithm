import java.util.*

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val N = br.readLine().toInt()
    var st = StringTokenizer(br.readLine())

    val len = LongArray(N - 1) { st.nextToken().toLong() }
    st = StringTokenizer(br.readLine())
    val oil = LongArray(N) { st.nextToken().toLong() }

    var sum = 0L
    var min = oil[0]

    for (i in 0 until N-1) {
        if (min > oil[i]) min = oil[i]
        sum += (min * len[i])
    }

    bw.append("$sum")

    bw.flush()
    bw.close()
}

