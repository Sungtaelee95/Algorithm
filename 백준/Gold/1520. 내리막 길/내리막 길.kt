import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Math.max
import java.util.*


var row = intArrayOf(0,0,-1,1)
var col = intArrayOf(-1,1,0,0)

lateinit var miro : Array<IntArray>
lateinit var visit : Array<IntArray>
var N = 0
var M = 0


fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    //var bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    N = st.nextToken().toInt()
    M = st.nextToken().toInt()

     miro = Array(N,{IntArray(M){0}})
     visit = Array(N,{IntArray(M){-1}})

    repeat(N){
        st = StringTokenizer(br.readLine())
        val index = it
        repeat(M){
            miro[index][it] = st.nextToken().toInt()
        }
    }

    visit[N-1][M-1] = 1
    println(dfs(0,0))

    //bw.flush()
    //bw.close()
}

fun dfs(r:Int,c:Int):Int{
    if(visit[r][c] > -1) return visit[r][c]

    visit[r][c] = 0

    for(i in 0 .. 3){
        val nr = row[i]+r
        val nc = col[i]+c
        if(nr < 0 || nc < 0 || nr >= N || nc >= M) continue
        if(miro[r][c] <= miro[nr][nc]) continue
        visit[r][c] += dfs(nr,nc)
    }

    return visit[r][c]
}


data class Info(
    val row:Int,
    val col:Int
)

