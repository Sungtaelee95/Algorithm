import java.io.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    
    val t = br.readLine().toInt()
    
    repeat(t) {
        val n = br.readLine().toInt()
        val (a,b) = br.readLine().split(" ").map{ it.toInt() }
        repeat(n) {
            val (u, v) = br.readLine().split(" ").map{ it.toInt() }
        }
        bw.appendLine("Material Management ${it+1}")
        bw.appendLine("Classification ---- End!")
    }
    
    bw.flush()
    bw.close()
}