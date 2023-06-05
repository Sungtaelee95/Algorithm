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

    val N = st.nextToken().toInt() // 도시의 개수
    st = StringTokenizer(br.readLine())
    val M = st.nextToken().toInt() // 버스의 개수
    val buslist = Array(N+1,{ArrayList<Info>()})

    repeat(M){
        st = StringTokenizer(br.readLine())
        buslist[st.nextToken().toInt()].add(Info(st.nextToken().toInt(),st.nextToken().toInt()))
    }
    st = StringTokenizer(br.readLine())
    val startcity = st.nextToken().toInt() // 출발 도시
    val endcity = st.nextToken().toInt() // 도착 도시
    val moneylist = IntArray(N+1){ Int.MAX_VALUE} // 도시 별 소요되는 돈 저장 배열
    val visit = BooleanArray(N+1)
    val pq = PriorityQueue<Info>(kotlin.Comparator { o1, o2 ->  o1.money.compareTo(o2.money)}) // 돈이 적은 순으로 정렬
    pq.add(Info(startcity,0)) // 출발 도시부터 출발도시는 소요비용 0
    moneylist[startcity] = 0

    while(!pq.isEmpty()){
        val now = pq.poll().target
        if(visit[now])continue // 이미 방문했던 도시라면 재방문하지 않음
        visit[now] = true

        for(next in buslist[now]){ // 현재 도시에서 갈 수 있는 도시 확인
            if(moneylist[next.target] > moneylist[now] + next.money){ // 다음 도시에 갈 수 있는 미리 저장된 돈과 현재 도시에서 다음도시에 갈 수 있는 비용 비교
                moneylist[next.target] = moneylist[now] + next.money
                pq.add(Info(next.target,moneylist[next.target])) // buslist[now]에서 (2,3),(2,1) 이런 식으로 나왔더라도 pq의 정렬 조건에 의해 추후 비교할때는 (2,1)이 먼저 나옴
                // 그렇기에 추후(2,1)을 이용하여 moneylist를 갱신하고 (2,3)이 나왔을 경우에는 상단 if문을 이용한 continue문 이후를 통과하지 못홤
                // 입력 순서만 따지면 (2,3),(2,1)이 맞지만 반대로 출력할때는 (2,1),(2,3) 으로 나옴
            }
        }
    }

    bw.append(moneylist[endcity].toString())

    bw.flush()
    bw.close()
}

data class Info(
    val target:Int,
    val money:Int
)

