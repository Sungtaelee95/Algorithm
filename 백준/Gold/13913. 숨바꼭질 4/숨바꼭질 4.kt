import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.ArrayDeque
import java.util.ArrayList
import java.util.LinkedList
import java.util.Stack
import java.util.StringTokenizer

data class Info(var position: Int, val count:Int)
fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val st = StringTokenizer(br.readLine())

    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()

    val dq = ArrayDeque<Info>()
    val load = IntArray(200_001){0}
    dq.addLast(Info(N, 0))

    val visit = BooleanArray(200_001)
    visit[N] = true

    val result = ArrayList<Int>()

    while(!dq.isEmpty()){
        val now = dq.pollFirst()
        if(now.position == M){
            bw.appendLine("${now.count}")
            for(i in 0 .. now.count){
                result.add(now.position)
                now.position = load[now.position]
            }
            break
        }

        if(now.position * 2 <= 200_000 &&  !visit[now.position * 2]){
            visit[now.position * 2] = true
            load[now.position*2] = now.position
            dq.addLast(Info(now.position*2, now.count + 1))
        }
        if(now.position + 1 <= 200_000 && !visit[now.position + 1]){
            visit[now.position + 1] = true
            load[now.position + 1] = now.position
            dq.addLast(Info(now.position+1, now.count + 1))
        }
        if(now.position - 1 >= 0 && !visit[now.position - 1]){
            visit[now.position - 1] = true
            load[now.position - 1] = now.position
            dq.addLast(Info(now.position-1, now.count + 1))
        }
    }

    result.reversed().forEach {
        bw.append("$it ")
    }

    bw.flush()
    bw.close()
}

