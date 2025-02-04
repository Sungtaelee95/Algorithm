import java.io.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    
    var first = br.readLine()
    var second = br.readLine()
    
    val list = mutableListOf<Char>()
    
    second.forEach { ch ->
        if (first.contains(ch)) list.add(ch)
        first = first.replaceFirst("$ch", "")
    }
    
    bw.append("${first.length + second.length - list.size}")
    
    bw.flush()
    bw.close()
}