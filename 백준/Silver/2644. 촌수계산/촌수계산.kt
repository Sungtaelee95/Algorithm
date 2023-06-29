import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.text.FieldPosition
import java.util.ArrayDeque
import java.util.LinkedList
import java.util.Stack
import java.util.StringTokenizer


data class Info(val position: Int, var count: Int)

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val N = br.readLine().toInt()
    var st = StringTokenizer(br.readLine())

    val start = st.nextToken().toInt()
    val end = st.nextToken().toInt()

    val map = Array(N+1) {ArrayList<Int>()}
    val visit = BooleanArray(N+1)

    repeat(br.readLine().toInt()){
        st = StringTokenizer(br.readLine())
        val parent = st.nextToken().toInt()
        val child = st.nextToken().toInt()

        map[parent].add(child)
        map[child].add(parent)
    }

    val dq = ArrayDeque<Info>()
    map[start].forEach{
        dq.add(Info(it, 1))
    }

    var result = -1

    while(!dq.isEmpty()){
        val now = dq.pollFirst()
        if(visit[now.position]) continue

        visit[now.position] = true

        if(now.position == end){
            result = now.count
            break
        }
        for(next in map[now.position]){
            if(!visit[next]){
                dq.addLast(Info(next,now.count+1))
            }
        }
    }

    bw.append("$result")

    bw.flush()
    bw.close()
}

