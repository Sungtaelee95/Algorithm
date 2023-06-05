import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*


fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    //var bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()

    var map = Array(N,{CharArray(M)})
    var visit = Array(N,{IntArray(M){1}})

    repeat(N){
        map[it] = br.readLine().toCharArray()
    }

    val ax = intArrayOf(0,0,-1,1)
    val ay = intArrayOf(1,-1,0,0)

    var dq = ArrayDeque<Info>()
    dq.addFirst(Info(0,0))
    while(!dq.isEmpty()){
        val now = dq.pollFirst()
        val nx = now.c
        val ny = now.r
        for(i in 0 .. 3){
            val nextX = ax[i]
            val nextY = ay[i]

            if(nx+nextX < 0 || ny+nextY < 0 || nx+nextX >= M || ny+nextY >= N) continue
            if(map[ny+nextY][nx+nextX] == '0') continue
            if(visit[ny+nextY][nx+nextX] != 1) continue

            visit[ny+nextY][nx+nextX] = visit[ny][nx]+1
            dq.addLast(Info(ny+nextY,nx+nextX))
        }
    }
    println(visit[N-1][M-1])

    //bw.flush()
    //bw.close()
}

data class Info(
    val r:Int,
    val c:Int
)


