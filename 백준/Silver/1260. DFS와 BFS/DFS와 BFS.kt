import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

lateinit var visit : BooleanArray
lateinit var node : Array<ArrayList<Int>>
var bw = BufferedWriter(OutputStreamWriter(System.out))

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val start = st.nextToken().toInt()

    node = Array(N+1,{ArrayList<Int>()})

    repeat(M){
        st = StringTokenizer(br.readLine())
        val one = st.nextToken().toInt()
        val two = st.nextToken().toInt()
        node[one].add(two)
        node[two].add(one)
    }

    for(arr in node){
        arr.sort()
    }

    visit = BooleanArray(N+1){true}
    dfs(start)
    bw.appendLine()

    visit = BooleanArray(N+1){true}
    var dq = ArrayDeque<Int>()
    dq.add(start)
    visit[start] = false
    while(!dq.isEmpty()){
        val now = dq.pollFirst()
        bw.append("$now ")
        for(i in 0 .. node[now].size-1){
            if(visit[node[now][i]]){
                dq.addLast(node[now][i])
                visit[node[now][i]] = false
            }
        }

    }
    bw.flush()
    bw.close()
}

fun dfs(now: Int){
    visit[now] = false
    bw.append("$now ")
    for(i in 0 .. node[now].size-1){
        if(visit[node[now][i]]){
            dfs(node[now][i])
        }
    }
}




