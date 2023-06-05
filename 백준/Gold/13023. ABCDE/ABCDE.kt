import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayList

lateinit var visit : BooleanArray
lateinit var friendList : Array<ArrayList<Int>>
var count = 0

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    //var bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()

    friendList = Array(N) { ArrayList<Int>() }

    repeat(M){
        st = StringTokenizer(br.readLine())
        val friend1 = st.nextToken().toInt()
        val friend2 = st.nextToken().toInt()
        friendList[friend1].add(friend2)
        friendList[friend2].add(friend1)
    }
    visit = BooleanArray(N){true}
    for(i in 0 ..visit.size-1){
        dfs(i,0)
        //println(visit.toMutableList())
        if(count == 1) break
    }
    println(count)
    //bw.flush()
    //bw.close()
}

fun dfs(fri:Int,deep: Int){
    visit[fri] = false
    if(deep == 4) {
        count = 1
        return
    }
    for(i in 0 .. friendList[fri].size-1){
        if(visit[ friendList[fri][i] ]){
            dfs(friendList[fri][i],deep+1)
        }
    }
    visit[fri] = true
}



