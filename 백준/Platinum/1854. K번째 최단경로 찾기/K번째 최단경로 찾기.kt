import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

lateinit var result: Array<PriorityQueue<Int>>
lateinit var lengthlist: Array<IntArray>
var K = 0

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())

    val N = st.nextToken().toInt() // 여행을 고려하고 있는 도시들의 개수
    val M = st.nextToken().toInt() // 도시 간에 존재하는 도로의 수
    K = st.nextToken().toInt() // 추후 K번째 최단 경로 구할 때 사용

    lengthlist = Array(N+1,{IntArray(N+1){0}}) // 도시 별 도착까지 걸리는 시간 저장

    repeat(M){
        st = StringTokenizer(br.readLine())
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        val c = st.nextToken().toInt()
        lengthlist[a][b] = c
    }

    result = Array(N+1,{ PriorityQueue<Int>(kotlin.Comparator { o1, o2 ->  o2.compareTo(o1)})}) // 도시 별 도착까지 걸리는 시간 저장

    dijkstra()

    for (i in 1 .. result.size-1){
        if(result[i].size != K) bw.appendLine("-1")
        else bw.appendLine(result[i].poll().toString())
    }

    bw.flush()
    bw.close()
}

fun dijkstra(){
    var pq = PriorityQueue<Info>(kotlin.Comparator { o1, o2 ->  o1.time.compareTo(o2.time)})
    pq.add(Info(1,0))
    result[1].add(0)

    while(!pq.isEmpty()){
        val info = pq.poll()
        val now = info.target
        val time = info.time
        for(next in 1 .. lengthlist[now].size-1){
            if(lengthlist[now][next] != 0){
                if(result[next].size < K){
                    result[next].add(time+lengthlist[now][next])
                    pq.add(Info(next,time+lengthlist[now][next]))
                } else {
                    if(result[next].peek() > time+lengthlist[now][next]){
                        result[next].poll()
                        result[next].add(time+lengthlist[now][next])
                        pq.add(Info(next,time+lengthlist[now][next]))
                    }
                }
            }
        }
    }
}

data class Info(
    val target:Int,
    val time:Int
)

