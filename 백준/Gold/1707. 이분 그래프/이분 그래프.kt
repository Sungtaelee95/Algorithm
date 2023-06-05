import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayList


fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    var K = st.nextToken().toInt()

    repeat(K){
        st = StringTokenizer(br.readLine())
        val V = st.nextToken().toInt()
        val E = st.nextToken().toInt()
        val arr = Array(V+1,{ArrayList<Int>()})
        repeat(E){
            st = StringTokenizer(br.readLine())
            val one = st.nextToken().toInt()
            val two = st.nextToken().toInt()
            arr[one].add(two)
            arr[two].add(one)
        }

        val red = 1
        var check = true
        var visit = IntArray(V+1){0}
        for(i in 1 .. V){
            if(visit[i] != 0) continue
            var dq = ArrayDeque<Int>()
            dq.addFirst(i)
            visit[i] = red
            while(!dq.isEmpty()){
                val now = dq.poll()
                for(j in 0 .. arr[now].size-1){
                    if(visit[arr[now][j]] == 0){
                        visit[arr[now][j]] = (visit[now] * -1)
                        dq.addLast(arr[now][j])
                    } else if(visit[arr[now][j]].equals(visit[now])){
                        check = false
                        break
                    }
                }
                if(!check) break
            }
        }
        if(!check){
            bw.appendLine("NO")
        } else {
            bw.appendLine("YES")
        }

    }

    bw.flush()
    bw.close()

}




