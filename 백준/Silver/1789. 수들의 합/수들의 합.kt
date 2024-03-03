fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val S = br.readLine().toLong()

    var count = 0L
    var sum = 0L

    for (i in 1..S + 1) {
        sum += i
        count = i
        if (sum > S) {
            count--
            break
        }
    }

    bw.append("$count")

    bw.flush()
    bw.close()
}
