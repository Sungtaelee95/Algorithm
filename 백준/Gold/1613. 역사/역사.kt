import java.util.*

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    var st = StringTokenizer(br.readLine())

    val N = st.nextToken().toInt()
    val K = st.nextToken().toInt()

    val loadMap = Array(N + 1) { BooleanArray(N + 1) }

    repeat(K) {
        st = StringTokenizer(br.readLine())
        val front = st.nextToken().toInt()
        val back = st.nextToken().toInt()
        loadMap[front][back] = true
    }

    for (k in 1..N) {
        for (i in 1..N) {
            if (i == k) continue
            for (j in 1..N) {
                if (i == j) continue
                if (loadMap[i][k] && loadMap[k][j] ) loadMap[i][j] = true
            }
        }
    }

    repeat(br.readLine().toInt()) {
        st = StringTokenizer(br.readLine())
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        when {
            loadMap[a][b] -> bw.appendLine("-1")
            loadMap[b][a] -> bw.appendLine("1")
            else -> bw.appendLine("0")
        }
    }

    bw.flush()
    bw.close()
}