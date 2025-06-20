fun main() {
    val br = System.`in`.bufferedReader()
    val requestCount = br.readLine().toInt()
    val dq = ArrayDeque<Long>()
    repeat(requestCount) {
        val input = br.readLine().toLong()
        if (input == 0L) dq.removeLast() else dq.addLast(input)
    }
    print(dq.sum())
}