import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

data class Info(val row: Int, val col: Int)
fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val arr = Array(N) {IntArray(M)}
    val visit = Array(N) {BooleanArray(M)}
    val nextRow = intArrayOf(0,0,1,-1)
    val nextCol = intArrayOf(1,-1,0,0)

    repeat(N){
        val temp = IntArray(M)
        st = StringTokenizer(br.readLine())
        repeat(M){
            temp[it] = st.nextToken().toInt()
        }
        arr[it] = temp
    }

    var count = 0
    var area = 0

    for(row in 0 until N){
        for(col in 0 until M){
            if(visit[row][col]) continue
            if(arr[row][col] == 0) continue

            count++
            val dq = ArrayDeque<Info>()
            dq.addFirst(Info(row, col))

            var tempArea = 1
            visit[row][col] = true
            while (!dq.isEmpty()){
                val now = dq.pollFirst()
                for (i in 0 .. 3){
                    val nr = now.row + nextRow[i]
                    val nc = now.col + nextCol[i]
                    if(nr < 0 || nc < 0 || nr >= N || nc >= M) continue
                    if(visit[nr][nc]) continue
                    if(arr[nr][nc] == 0) continue
                    tempArea++
                    visit[nr][nc] = true
                    dq.addLast(Info(nr, nc))
                }
            }
            area = Math.max(area, tempArea)
        }
    }

    bw.append("$count\n$area")
    bw.flush()
    bw.close()
}