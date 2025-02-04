import java.io.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    repeat(br.readLine().toInt()) {
        bw.appendLine("Material Management ${it+1}")
        bw.appendLine("Classification ---- End!")
    }
    
    bw.flush()
    bw.close()
}