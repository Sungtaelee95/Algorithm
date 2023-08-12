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
    val M = st.nextToken().toInt()

    val farmList = Array(N+1) {ArrayList<Int>()}
    val visit = IntArray(N+1)

    repeat(M){
        st = StringTokenizer(br.readLine())
        val start = st.nextToken().toInt()
        val end = st.nextToken().toInt()
        farmList[start].add(end)
        farmList[end].add(start)
    }

    val dq = ArrayDeque<Info>()
    visit[1] = -1
    for(next in farmList[1]){
        dq.addLast(Info(next, 1))
        visit[next] = 1
    }
    while(!dq.isEmpty()){
        val info = dq.poll()
        val now = info.now
        val deep = info.deep

        for(farm in farmList[now]){
            if(visit[farm] != 0) continue
            visit[farm] = deep + 1
            dq.addLast(Info(farm, deep + 1))
        }
    }

    val number = visit.indexOfFirst { i -> i == visit.max()}
    val length = visit[number]
    val count = visit.count{i -> i == length}

    bw.append("$number $length $count")

    bw.flush()
    bw.close()
}

data class Info(val now: Int, val deep: Int)