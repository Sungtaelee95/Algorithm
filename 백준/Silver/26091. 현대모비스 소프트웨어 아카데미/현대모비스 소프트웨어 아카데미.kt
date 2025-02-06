import java.io.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    
    val (n,m) = br.readLine().split(" ").map{it.toInt()}
    val teams = br.readLine().split(" ").map{it.toInt()}.sortedBy{it}
    var s = 0
    var e = n-1
    var result = 0
    while(s < e) {
        if (teams[s] + teams[e] >= m) {
            result++
            s++
            e--
            continue
        }
        s++
    }
    bw.append("$result")
    bw.flush()
    bw.close()
}