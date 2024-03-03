fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    var N = br.readLine().toInt()

    var count = 0

    if (N % 5 == 0) {
        count += N / 5
        bw.append("$count")
    } else {
        while (N > 0) {
            N -= 2
            count++
            if (N % 5 == 0) {
                count += N / 5
                bw.append("$count")
                break
            }
        }
    }
    if (N < 0) bw.append("-1")

    bw.flush()
    bw.close()
}

