import java.io.*
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (n, k) = br.readLine().split(" ").map{ it.toInt() }

    val pq = PriorityQueue<Info>(
        Comparator{ o1, o2 ->
            if (o1.g == o2.g) {
                if (o1.s == o2.s) {
                    o2.b.compareTo(o1.b)
                } else {
                    o2.s.compareTo(o1.s)
                }
            } else {
                o2.g.compareTo(o1.g)
            }
        }
    )
    var target = Info(0,0,0)
    repeat(n) {
        val (num,g,s,b) = br.readLine().split(" ").map{it.toInt()}
        pq.add(Info(g,s,b))
        if (num == k) target = Info(g,s,b)
    }

    var rank = 1
    while(pq.isNotEmpty()) {
        val now = pq.poll()
        if (now == target) {
            bw.append("$rank")
            break
        }
        rank++
    }
    bw.flush()
    bw.close()
}

data class Info(val g: Int, val s: Int, val b: Int)