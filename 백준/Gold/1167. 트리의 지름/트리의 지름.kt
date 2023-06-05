import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Math.max
import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    //var bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    var N = st.nextToken().toInt()

    var arr = Array(N+1) { kotlin.collections.ArrayList<Info>() }
    var visit = BooleanArray(N+1){true}
    var dist = IntArray(N+1){0}
    repeat(N){
        st = StringTokenizer(br.readLine())
        val start = st.nextToken().toInt()
        while(true){
            val end = st.nextToken().toInt()
            if(end == -1) break
            val len = st.nextToken().toInt()
            arr[start].add(Info(start,end,len))
        }
    }

    var dq = ArrayDeque<Int>()
    dq.add(1)
    while(!dq.isEmpty()){
        val now = dq.pollFirst()
        visit[now] = false

        for(next in arr[now]){
            if(visit[next.end]){
                visit[next.end] = false
                dist[next.end] = dist[now] + next.len
                dq.addLast(next.end)
            }
        }
    }

    val start = dist.indexOf(dist.maxOrNull()!!)
    visit = BooleanArray(N+1){true}
    dist = IntArray(N+1){0}
    dq.add(start)
    while(!dq.isEmpty()){
        val now = dq.pollFirst()
        visit[now] = false

        for(next in arr[now]){
            if(visit[next.end]){
                visit[next.end] = false
                dist[next.end] = dist[now] + next.len
                dq.addLast(next.end)
            }
        }
    }
    println(dist.maxOrNull()!!)
    //bw.flush()
    //bw.close()
}

data class Info(
    val start:Int,
    val end:Int,
    val len: Int
)
