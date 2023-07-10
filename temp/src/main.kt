import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.ArrayDeque
import java.util.LinkedList
import java.util.Stack
import java.util.StringTokenizer

data class Info(val position: Int, val count:Int)
fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val st = StringTokenizer(br.readLine())

    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()

    val dq = ArrayDeque<Info>()
    val loadMap = IntArray(200001){0}
    dq.addLast(Info(N, 0))
    val visit = IntArray(200001){0}
    visit[N] = 1
    var count = 0

    while(!dq.isEmpty()){
        val now = dq.pollFirst()
        if(now.position == M){
            bw.appendLine("${now.count}")
            count = now.count
            break
        }

        if(now.position * 2 <= M && (visit[now.position * 2] > now.count+1 || visit[now.position * 2] == 0) ){
            visit[now.position * 2] = visit[now.position]+1
            dq.addLast(Info(now.position*2, now.count + 1,))
        }
        if(now.position + 1 <= M && (visit[now.position + 1] > now.count + 1 || visit[now.position + 1] == 0)){
            visit[now.position + 1] =visit[now.position]+1
            dq.addLast(Info(now.position+1, now.count + 1))
        }
        if(now.position - 1 >= 0 && (visit[now.position - 1] > now.count + 1 || visit[now.position - 1] == 0)){
            visit[now.position - 1] = visit[now.position]+1
            dq.addLast(Info(now.position-1, now.count + 1))
        }
    }

    for(i in 1 .. 10){
        print("${loadMap[i]} ")
    }

    dq.clear()
//    dq.addLast(Info(M, count+1))
//
//    while(!dq.isEmpty()){
//
//    }

    bw.flush()
    bw.close()
}

