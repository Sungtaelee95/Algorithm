import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    //var bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()

    var pq = PriorityQueue<time>(kotlin.Comparator { o1, o2 -> if(o1.end == o2.end) o1.start.compareTo(o2.start) else o1.end.compareTo(o2.end)})

    repeat(N){
        st = StringTokenizer(br.readLine())
        val start = st.nextToken().toInt()
        val end = st.nextToken().toInt()
        pq.add(time(start,end))
    }
    var count = 0
    var nowe = 0
    while(!pq.isEmpty()){
        val next = pq.poll()
        val nexts = next.start
        val nexte = next.end
        if(nowe <= nexts){
            nowe = nexte
            count++
        }
    }
    println(count)

    //bw.flush()
    //bw.close()
}

data class time(
    val start: Int,
    val end : Int
)