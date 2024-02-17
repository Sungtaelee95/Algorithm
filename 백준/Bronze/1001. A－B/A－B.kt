import java.util.*

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    var st = StringTokenizer(br.readLine())

    val N = st.nextToken().toInt()
    val K = st.nextToken().toInt()

    bw.append("${N-K}")

    bw.flush()
    bw.close()
}