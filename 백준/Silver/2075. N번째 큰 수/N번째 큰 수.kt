import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())

    var pq = PriorityQueue<Int>(reverseOrder())

    val N = st.nextToken().toInt()
    repeat(N){
        st = StringTokenizer(br.readLine())
        repeat(N){
            pq.add(st.nextToken().toInt())
        }
    }

    repeat(N-1){
        pq.poll()
    }

    bw.append("${pq.poll()}")

    bw.flush()
    bw.close()
}