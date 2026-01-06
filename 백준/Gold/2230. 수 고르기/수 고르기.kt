fun main() {
    val br = System.`in`.bufferedReader()
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val input = IntArray(n) { br.readLine().toInt() }.sorted()
    var start = 0
    var end = 0
    var result = 2_000_000_001
    while (end < n && start < n) {
        val temp = input[end] - input[start]
        if (temp < m) {
            end++
        } else {
            result = minOf(result, temp)
            start++
        }
    }
    println(result)
}
