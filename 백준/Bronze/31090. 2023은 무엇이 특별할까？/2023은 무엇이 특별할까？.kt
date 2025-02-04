import java.io.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val t = br.readLine().toInt()
    
    repeat(t) {
        val input = br.readLine().toInt()
        val one = input % 10
        val two = input % 100 - one
        if ((input + 1) % (one + two) == 0) {
            bw.appendLine("Good")
        } else { 
            bw.appendLine("Bye")
        }
    }
    
    bw.flush()
    bw.close()
}