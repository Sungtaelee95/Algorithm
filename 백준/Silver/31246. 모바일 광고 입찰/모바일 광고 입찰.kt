import java.io.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (n,k) = br.readLine().split(" ").map{ it.toInt() }
    val ads = Array<Ad>(n) {Ad(0,0)}
    repeat(n) {
        val (a,b) = br.readLine().split(" ").map{ it.toInt() }
        ads[it] = (Ad(a,b))
    }
    ads.sortWith{ o1, o2 -> (o1.b - o1.a).compareTo(o2.b - o2.a)}
    if (ads[k-1].b - ads[k-1].a <= 0) bw.append("0") else bw.append("${ads[k-1].b - ads[k-1].a}")

    bw.flush()
    bw.close()
}

data class Ad(val a: Int, val b: Int)