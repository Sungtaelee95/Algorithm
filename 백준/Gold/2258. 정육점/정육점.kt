import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.min

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())

    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    val pq = PriorityQueue<Info> { o1, o2 -> if (o1.price == o2.price) o2.kg.compareTo(o1.kg) else o1.price.compareTo(o2.price) }

    repeat(n) {
        st = StringTokenizer(br.readLine())
        pq.add(Info(st.nextToken().toInt(), st.nextToken().toInt()))
    }

    var results = PriorityQueue<Int>()
    var price = 0
    var tempPrice = 0
    var kgSum = 0
    while(pq.isNotEmpty()) {
        val now = pq.poll()
        if (now.price == price) {
            tempPrice += now.price
            kgSum += now.kg
            if (kgSum >= m) {
                results.add(tempPrice)
            }
            continue
        }
        price = now.price
        tempPrice = now.price
        kgSum += now.kg
        if (kgSum >= m) {
            results.add(now.price)
            break
        }
    }

    if (results.isEmpty()) {
        bw.append("-1")
    } else {
        bw.append("${results.peek()}")
    }


    bw.flush()
    bw.close()
}

data class Info(val kg: Int, val price: Int)