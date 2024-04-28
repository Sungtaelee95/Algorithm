import java.util.*

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    var st = StringTokenizer(br.readLine())

    val n = st.nextToken().toInt()

    val arr = Array(n + 1) { IntArray(n + 1) { 100_000_000 } }

    while (true) {
        st = StringTokenizer(br.readLine())
        val t1 = st.nextToken().toInt()
        val t2 = st.nextToken().toInt()
        if (t1 == -1 && t2 == -1) break
        arr[t1][t2] = 1
        arr[t2][t1] = 1
    }

    for (i in 1..n) {
        for (j in 1..n) {
            if (i == j) arr[i][j] = 0
        }
    }

    for (k in 1..n) {
        for (i in 1..n) {
            for (j in 1..n) {
                if (arr[i][j] > arr[i][k] + arr[k][j]) {
                    arr[i][j] = arr[i][k] + arr[k][j]
                }
            }
        }
    }

    for (i in 0..n) {
        for (j in 0..n) {
            if (arr[i][j] == 100_000_000) arr[i][j] = 0
        }
    }
    var max = 100_000_000
    var results = mutableListOf<Int>()
    for (i in 1..n) {
        if (max > arr[i].max()) {
            max = arr[i].max()
            results.clear()
        }
        if (max == arr[i].max()) {
            results.add(i)
        }
    }
    bw.appendLine("$max ${results.size}")
    results.forEach {
        bw.append("$it ")
    }


    bw.flush()
    bw.close()
}

