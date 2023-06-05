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

    var pq = PriorityQueue<Int>(kotlin.Comparator { o1, o2 ->  o2.compareTo(o1)})
    var pq2 = PriorityQueue<Int>()

    repeat(N){
        st = StringTokenizer(br.readLine())
        var value = st.nextToken().toInt()
        if(value <= 0){
            pq2.add(value)
        } else {
            pq.add(value)
        }
    }
    var sum = 0
    while (pq.size > 1){
        var temp1 = pq.poll()
        var temp2 = pq.poll()
        if(temp1*temp2 > temp1+temp2){
            sum += temp1*temp2
            continue
        }
        sum += temp1+temp2
    }
    while(!pq.isEmpty()){
        sum += pq.poll()
    }

    while (pq2.size > 1){
        var temp1 = pq2.poll()
        var temp2 = pq2.poll()
        if(temp1*temp2 > temp1+temp2){
            sum += temp1*temp2
            continue
        }
        sum += temp1+temp2
    }
    while(!pq2.isEmpty()){
        sum += pq2.poll()
    }

    println(sum)

    //bw.flush()
    //bw.close()
}