import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

lateinit var fireMap : Array<IntArray>
lateinit var visit : Array<BooleanArray>
lateinit var visit2 : Array<BooleanArray>
lateinit var start : Position
val dq = ArrayDeque<Position>()

var exitCheck = false
var exitCount = 0

val br = BufferedReader(InputStreamReader(System.`in`))
val bw = BufferedWriter(OutputStreamWriter(System.out))
var st = StringTokenizer(br.readLine())

fun main(){

    // 케이스의 수를 입력 받는다.
    val case = st.nextToken().toInt()

    repeat(case){
        st = StringTokenizer(br.readLine())
        val mapCol = st.nextToken().toInt()
        val mapRow = st.nextToken().toInt()
        fireMap = Array(mapRow) { IntArray(mapCol) { 1100 } }
        visit = Array(mapRow) { BooleanArray(mapCol){true} }
        visit2 = Array(mapRow) { BooleanArray(mapCol){true} }
        for(row in 0 .. mapRow-1){
            val line = br.readLine()
            for(col in 0 .. line.length-1){
                if(line[col] == '#'){
                    visit[row][col] = false
                    visit2[row][col] = false
                }
                if(line[col] == '*'){
                    dq.add(Position(row,col))
                    fireMap[row][col] = 0
                    visit2[row][col] = false
                }
                if(line[col] == '@'){
                    start = Position(row,col)
                }
            }
        }
        // 불번짐

        fireBfs()

        // 탈출확인
        exitCheck = false
        fireMap[start.row][start.col] = 0
        dq.add(start)
        exitBfs()

        // 탈출 후 현재 dq에 남은 자료 클리어.

        if(exitCheck){
            bw.appendLine("$exitCount")
        } else {
            bw.appendLine("IMPOSSIBLE")
        }

    }

    bw.flush()
    bw.close()
}

val rowArr = intArrayOf(0,0,1,-1)
val colArr = intArrayOf(1,-1,0,0)

fun fireBfs(){

    while (!dq.isEmpty()){
        val now = dq.poll()
        visit[now.row][now.col] = false
        for(i in 0 .. 3){
            val nr = now.row + rowArr[i]
            val nc = now.col + colArr[i]
            if(nr < 0 || nc < 0 || nr >= fireMap.size || nc >= fireMap[0].size) continue
            if(!visit[nr][nc]) continue
            visit[nr][nc] = false
            dq.addLast(Position(nr,nc))
            fireMap[nr][nc] = fireMap[now.row][now.col]+1
        }
    }
}

fun exitBfs(){
    while(!dq.isEmpty()){
        val now = dq.poll()
        visit2[now.row][now.col] = false

        for(i in 0 .. 3){
            val nr = now.row + rowArr[i]
            val nc = now.col + colArr[i]

            if(nr < 0 || nc < 0 || nr >= fireMap.size || nc >= fireMap[0].size) {
                exitCheck = true
                exitCount = fireMap[now.row][now.col]+1
                break
            }

            if( fireMap[nr][nc] <= fireMap[now.row][now.col]+1) continue
            if(!visit2[nr][nc]) continue
            visit2[nr][nc] = false
            dq.addLast(Position(nr,nc))
            fireMap[nr][nc] = fireMap[now.row][now.col]+1
        }
        if(exitCheck)break
    }
}

data class Position(val row:Int, val col:Int)





















