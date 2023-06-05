import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.sqrt

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    //var bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val K = st.nextToken().toInt()
    val X = st.nextToken().toInt()

    var visit = BooleanArray(N+1){true}
    var count = IntArray(N+1){0}

    var loadList = ArrayList<ArrayList<Int>>()
    repeat(N+1){
        loadList.add(ArrayList<Int>())
    }
    repeat(M){
        st = StringTokenizer(br.readLine())
        loadList[st.nextToken().toInt()].add(st.nextToken().toInt())
    }

    var dq = ArrayDeque<Int>()
    visit[X] = false
    for(load in loadList[X]){
        dq.add(load)
        count[load] = count[X]+1
        visit[load] = false
    }
    while (!dq.isEmpty()){
        val now = dq.pollFirst()
        for(load in loadList[now]){
            if(visit[load]){
                dq.addLast(load)
                count[load] = count[now]+1
                visit[load] = false
            }
        }
    }

    var result = ArrayList<Int>()
    for(i in 1 .. count.size-1) if(count[i] == K)result.add(i)

    if(result.isEmpty()){
        println(-1)
    } else {
        result.forEach{
            println(it)
        }
    }

    //bw.flush()
    //bw.close()
}
