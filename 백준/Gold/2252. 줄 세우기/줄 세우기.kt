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
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    var degree = IntArray(n+1,{0}) // 진입차수를 저장 할 배열
    var next = Array(n+1, {ArrayList<Int>()}) // 현재 노드에서 갈 수 있는 노드리스트 저장할 배열
    repeat(m){
        st = StringTokenizer(br.readLine())
        var front = st.nextToken().toInt() // 앞에 있어야 할 숫자
        var back = st.nextToken().toInt() // 뒤에 있어야 할 숫자
        degree[back]++
        next[front].add(back)
    }

    var dq = ArrayDeque<Int>()
    for(i in 1 .. degree.size-1){
        if(degree[i] == 0) {
            dq.add(i)
        }
    }

    while(!dq.isEmpty()){
        val now = dq.pollFirst()
        bw.append("$now ")
        for(i in next[now]){
            degree[i]--
            if(degree[i] == 0){
                dq.addLast(i)
            }
        }

    }

    bw.flush()
    bw.close()
}

