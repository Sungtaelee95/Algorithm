fun main() {
    val br = System.`in`.bufferedReader()
    val requestCount = br.readLine().toInt()
    repeat(requestCount) {
        val dq = ArrayDeque<Char>()
        val input = br.readLine()
        for (i in input) {
            if (i == '(') {
                dq.add(i)
                continue
            }
            if (i == ')') {
                if (dq.isNotEmpty() && dq.last() == '(') {
                    dq.removeLast()
                    continue
                }
                dq.add(i)
            }
        }
        if (dq.isEmpty()) println("YES") else println("NO")
    }
}