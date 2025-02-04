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
                    if (o1.b == o2.b) {
                        o1.num.compareTo(o2.num)
                    } else {
                        o2.b.compareTo(o1.b)
                    }
                } else {
                    o2.s.compareTo(o1.s)
                }
            } else {
                o2.g.compareTo(o1.g)
            }
        }
    )
    repeat(n) {
        val (num,g,s,b) = br.readLine().split(" ").map{it.toInt()}
        pq.add(Info(num,g,s,b))
    }
    var last = Info(0,1_000_001,0,0)
    var rank = 0
    var equals = 0
    while(pq.isNotEmpty()) {
        val now = pq.poll()
        if (last.g == now.g && last.s == now.s && last.b == now.b) {
            equals++
        } else {
            if (equals == 0){
                 rank++ 
            }else {
                rank += equals
                equals = 0
            }
        }
        if (now.num == k) {
            bw.append("$rank")
            break
        }
        last = now
    }
    bw.flush()
    bw.close()
}

data class Info(val num: Int, val g: Int, val s: Int, val b: Int)