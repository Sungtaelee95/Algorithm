import java.io.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val dq = ArrayDeque<Int>()
    repeat(br.readLine().toInt()) {
        val commend = br.readLine().split(" ")
        when(commend[0]) {
            "push" -> dq.addLast(commend[1].toInt())
            "pop" -> { 
                if (dq.isEmpty()) {
                    bw.appendLine("-1")
                } else {
                    bw.appendLine("${dq.removeLast()}")
                }
            }
            "size" -> bw.appendLine("${dq.size}")
            "empty" -> {
                if (dq.isEmpty()) {
                    bw.appendLine("1")
                } else {
                    bw.appendLine("0")
                }
            }
            "top" ->{
                if (dq.isEmpty()) {
                    bw.appendLine("-1")
                } else {
                    bw.appendLine("${dq.last()}")
                }
            } 
        }
    }
    bw.flush()
    bw.close()
}