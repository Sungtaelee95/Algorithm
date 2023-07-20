import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.sqrt


val visit = BooleanArray(10001)
val bw = BufferedWriter(OutputStreamWriter(System.out))
fun main(){
//    val br = BufferedReader(InputStreamReader(System.`in`))

    for(num in 1 .. 10000){
        bfs(num)
    }

    for(i in 1 .. 10000){
        if(!visit[i]) bw.appendLine("$i")
    }

    bw.flush()
    bw.close()
}

fun bfs(num: Int){
    if(visit[num]) return
    val dq = ArrayDeque<Int>()
    dq.addFirst(num)

    while(!dq.isEmpty()){
        val now = dq.pollFirst()
        var next = 0
        next += now
        now.toString().forEach {
            next += it-'0'
        }
        if(next in 1 .. 10000 && !visit[next]){
            visit[next] = true
            dq.addLast(next)
        }
    }

}


