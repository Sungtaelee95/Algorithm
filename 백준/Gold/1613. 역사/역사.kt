import java.util.*

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    var st = StringTokenizer(br.readLine())

    val N = st.nextToken().toInt()
    val K = st.nextToken().toInt()

    val loadMap = Array(N + 1) { IntArray(N + 1) { 0 } }

    repeat(K) {
        st = StringTokenizer(br.readLine())
        val front = st.nextToken().toInt()
        val back = st.nextToken().toInt()
        loadMap[front][back] = -1
        loadMap[back][front] = 1
    }

    for (k in 1..N) {
        for (i in 1..N) {
            for (j in 1..N) {
                if (loadMap[i][k] == -1 && loadMap[k][j] == -1) loadMap[i][j] = -1
                if (loadMap[i][k] == 1 && loadMap[k][j] == 1) loadMap[i][j] = 1
            }
        }
    }

    repeat(br.readLine().toInt()) {
        st = StringTokenizer(br.readLine())
        bw.appendLine("${loadMap[st.nextToken().toInt()][st.nextToken().toInt()]}")
    }

    bw.flush()
    bw.close()
}