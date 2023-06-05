import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    val V = st.nextToken().toInt()
    val e = st.nextToken().toInt()
    st = StringTokenizer(br.readLine())
    val s = st.nextToken().toInt() // 출발노드
    val distance = IntArray(V+1){ Int.MAX_VALUE} // 거리 저장할 배열
    val visit = BooleanArray(V+1)
    val pq = PriorityQueue<Pair<Int,Int>>(kotlin.Comparator { o1, o2 ->  o1.first.compareTo(o2.first)})
    val nodelist = Array(V+1,{ArrayList<Info>()}) // 각 노드 별 갈 수 있는 정점 리스트

    repeat(e){
        st = StringTokenizer(br.readLine())
        val u = st.nextToken().toInt()
        val v = st.nextToken().toInt()
        val w = st.nextToken().toInt()
        nodelist[u].add(Info(v,w))
    }

    pq.add(Pair(0,s)) // 가중치,시작점
    distance[s] = 0
    while(!pq.isEmpty()){
        val Info = pq.poll()
        val now = Info.second// 현재 위치
        if(visit[now])continue
        visit[now] = true

        for(i in nodelist[now]){
            val next = i.target
            val value = i.current
            if(distance[next] > distance[now] + value){
                distance[next] = distance[now] + value
                pq.add(Pair(distance[next],next))
            }
        }
    }

    for(i in 1 .. visit.size-1){
        if(visit[i]) bw.appendLine(distance[i].toString())
        else bw.appendLine("INF")
    }
    
    bw.flush()
    bw.close()
}

data class Info(
    val target:Int, // 다음 정점
    val current:Int // 가중치
)