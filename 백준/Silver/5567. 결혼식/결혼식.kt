import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayList

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val N = br.readLine().toInt()

    val friendLinkList = Array(N+1) {ArrayList<Int>()}
    val visit = BooleanArray(N+1)

    repeat(br.readLine().toInt()){
        val st = StringTokenizer(br.readLine())
        val friend1 = st.nextToken().toInt()
        val friend2 = st.nextToken().toInt()
        friendLinkList[friend1].add(friend2)
        friendLinkList[friend2].add(friend1)
    }

    var count = 0
    val dq = ArrayDeque<Int>()
    visit[1] = true

    for(myFrined in friendLinkList[1]){
        dq.addLast(myFrined)
        visit[myFrined] = true
        count++
    }

    while(!dq.isEmpty()){
        val now = dq.pollFirst()
        for(next in friendLinkList[now]){
            if(!visit[next]){
                visit[next] = true
                count++
            }
        }
    }

    bw.append("$count")

    bw.flush()
    bw.close()
}