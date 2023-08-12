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

    val comLinkList = Array(N+1) {ArrayList<Int>()}
    val visit = BooleanArray(N+1)

    repeat(br.readLine().toInt()){
        val st = StringTokenizer(br.readLine())
        val com1 = st.nextToken().toInt()
        val com2 = st.nextToken().toInt()
        comLinkList[com1].add(com2)
        comLinkList[com2].add(com1)
    }

    var count = 0

    val dq = ArrayDeque<Int>()
    dq.add(1)
    visit[1] = true

    while(!dq.isEmpty()){
        val now = dq.pollFirst()
        for(next in comLinkList[now]){
            if(!visit[next]){
                dq.addLast(next)
                visit[next] = true
                count++
            }
        }
    }

    bw.append("$count")

    bw.flush()
    bw.close()
}