import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    //val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    st = StringTokenizer(br.readLine())
    val m = st.nextToken().toInt()

    var ar = Array(n+1,{ArrayList<Info>()}) // 도시 인접 리스트
    var arreverse =Array(n+1,{ArrayList<Info>()}) // 역방향 도시 인접 리스트 -> 추후 1분도 쉬지 않고 달려야 하는 도로의 수 찾을 때 사용
    var indegree = IntArray(n+1){0} // 진입 차수 배열

    repeat(m){
        st = StringTokenizer(br.readLine())
        val start = st.nextToken().toInt()
        val end = st.nextToken().toInt()
        val time = st.nextToken().toInt()

        ar[start].add(Info(end,time))
        arreverse[end].add(Info(start,time))
        indegree[end]++
    }

    st = StringTokenizer(br.readLine())

    var startcity = st.nextToken().toInt() // 출발하는 도시
    var endcity = st.nextToken().toInt() // 도착하는 도시

    var dq = ArrayDeque<Int>()
    dq.add(startcity)
    var result = IntArray(n+1)
    while(!dq.isEmpty()){
        val now = dq.pollFirst()
        for(i in ar[now]){
            indegree[i.targetNode]--
            result[i.targetNode] = Math.max(result[i.targetNode],result[now]+i.value)
            if(indegree[i.targetNode] == 0){
                dq.addLast(i.targetNode)
            }
        }

    }

    var resultCount = 0
    var visit = BooleanArray(n+1)
    dq = ArrayDeque<Int>()
    dq.add(endcity)
    visit[endcity] = true

    while(!dq.isEmpty()){
        val now = dq.pollFirst()
        for(i in arreverse[now]){
            if(result[i.targetNode] + i.value == result[now]){
                resultCount++
                if(visit[i.targetNode] == false){
                    visit[i.targetNode] = true
                    dq.addLast(i.targetNode)
                }
            }
        }
    }

    println(result[endcity])
    println(resultCount)

    //bw.flush()
    //bw.close()
}

data class Info(
    var targetNode:Int,
    var value:Int
)