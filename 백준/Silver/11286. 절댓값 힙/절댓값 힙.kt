import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.abs

fun main() {
//    val s = System.currentTimeMillis()
    var br = BufferedReader(InputStreamReader(System.`in`))
    var bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt() // 자료의 개수
    //var sb = StringBuilder()

    var pq1 = PriorityQueue<Int>(kotlin.Comparator { o1, o2 -> abs(o1).compareTo(abs(o2))}) // 양수
    var pq2 = PriorityQueue<Int>(kotlin.Comparator { o1, o2 -> abs(o1).compareTo(abs(o2))}) // 음수

    repeat(N){
        st = StringTokenizer(br.readLine())
        val value = st.nextToken().toInt()
        if(value == 0){
            if(pq1.isEmpty() && pq2.isEmpty()){
                bw.appendLine("0")
            } else {
                if(pq2.isEmpty()){
                    bw.appendLine("${pq1.poll()}")
                }else if(pq1.isEmpty()){
                    bw.appendLine("${pq2.poll()}")
                }else if(abs(pq2.peek()) > abs(pq1.peek()) ) {
                    bw.appendLine("${pq1.poll()}")
                } else {
                    bw.appendLine("${pq2.poll()}")
                }
            }
        } else {
            if(value < 0){
                pq2.add(value)
            } else {
                pq1.add(value)
            }
        }
    }

    bw.flush()
    bw.close()

//   val e = System.currentTimeMillis()
//   println((e-s)/1000.0)

}


