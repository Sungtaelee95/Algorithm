import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*


val arr = intArrayOf(0,0,-1,1) // row
val arc = intArrayOf(-1,1,0,0) // col
lateinit var road : Array<CharArray>
var visit = BooleanArray(26) // A = 65 / 0, Z = 90 / 25
var answer = 0
var r = 0
var c = 0

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    //var bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())

    r = st.nextToken().toInt()
    c = st.nextToken().toInt()

    road = Array(r,{ charArrayOf() })

    repeat(r){
        road[it] = br.readLine().toString().toCharArray()
    }

    visit[road[0][0]-'A'] = true
    dfs(0,0,1)

    println(answer)

    //bw.flush()
    //bw.close()

}

fun dfs(row:Int,col:Int,count:Int){

    answer = Math.max(answer,count)

    for(i in 0 .. 3){
        val nr = row + arr[i]
        val nc = col + arc[i]

        if(nr < 0 || nr >= r || nc < 0 || nc >= c) continue
        if(visit[road[nr][nc]-'A'] == true) continue
        visit[road[nr][nc]-'A'] = true
        dfs(nr,nc,count+1)
        visit[road[nr][nc]-'A'] = false
    }

}
