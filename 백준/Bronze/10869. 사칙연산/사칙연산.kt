import java.io.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val (a,b) = br.readLine().split(" ").map{it.toInt()}
    
    bw.appendLine("${a+b}")
    bw.appendLine("${a-b}")
    bw.appendLine("${a*b}")
    bw.appendLine("${a/b}")
    bw.appendLine("${a%b}")
    
    bw.flush()
    bw.close()
}