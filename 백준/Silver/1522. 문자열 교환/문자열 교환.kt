fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val input = br.readLine().toCharArray()
    val check = input + input
    var result = Int.MAX_VALUE
    val aCount = input.count { it == 'a' }

    for (i in input.indices) {
        var bCount = 0
        for (j in i until i+aCount) {
            if (check[j] == 'b') bCount++
        }
        result = Math.min(result, bCount)
    }

    bw.append("$result")

    bw.flush()
    bw.close()
}
