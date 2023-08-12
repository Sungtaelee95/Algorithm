import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayList

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()

    val LinkList = Array(N) {ArrayList<Int>()}
    val visit = Array(N){IntArray(N)}

    repeat(N){
        st = StringTokenizer(br.readLine())
        for (j in 0 .. N-1){
            if(st.nextToken().toInt() == 1){
                LinkList[it].add(j)
            }
        }
    }

   for(i in 0 .. LinkList.size-1){
       for(j in 0 .. LinkList[i].size-1){
           val next = LinkList[i][j]
           val dq = ArrayDeque<Int>()
           dq.addLast(next)
           visit[i][next] = 1
           while (!dq.isEmpty()){
               val now = dq.pollFirst()
               for(k in LinkList[now]){
                   if(visit[i][k] == 0){
                       visit[i][k] = 1
                       dq.addLast(k)
                   }
               }
           }
       }
   }

    visit.forEach {
        it.forEach {
            bw.append("$it ")
        }
        bw.appendLine()
    }

    bw.flush()
    bw.close()
}