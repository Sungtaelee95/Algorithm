import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.text.FieldPosition
import java.util.ArrayDeque
import java.util.ArrayList
import java.util.LinkedList
import java.util.Stack
import java.util.StringTokenizer


data class Info(val high: Int, val row: Int, val col: Int, val time: Int)

val highArr = intArrayOf(-1, 1)
val rowArr = intArrayOf(0, 0, -1, 1)
val colArr = intArrayOf(-1, 1, 0, 0)

var mapCol = 0
var mapRow = 0
var mapHigh = 0
val dq = ArrayDeque<Info>()

lateinit var map : Array<Array<IntArray>>
lateinit var dayMap : Array<Array<IntArray>>

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())

    // 1을 만나면 bfs 실행
    // dfs 실행 중에 1을 만날 경우는 넘김.
    // 마지막에 0이 남아있다면 -1 반환

    mapCol = st.nextToken().toInt()
    mapRow = st.nextToken().toInt()
    mapHigh = st.nextToken().toInt()

    map = Array(mapHigh){Array(mapRow){IntArray(mapCol)}}
    dayMap = Array(mapHigh){Array(mapRow){IntArray(mapCol){1_000_001}}}

    for(high in 0 until mapHigh){
        for(row in 0 until mapRow){
            st = StringTokenizer(br.readLine())
            for(col in 0 until mapCol){
                map[high][row][col] = st.nextToken().toInt()
            }
        }
    }

    for(high in 0 until mapHigh){
        for(row in 0 until mapRow){
            for(col in 0 until mapCol){
                if(map[high][row][col] == 1) {
                    dayMap[high][row][col] = 0
                    dq.add(Info(high, row, col, 0))
                    bfs()
                }
            }
        }
    }

    var result = 0

    for(high in 0 until mapHigh){
        for(row in 0 until mapRow){
            for(col in 0 until mapCol){
                if(map[high][row][col] == -1) dayMap[high][row][col] = 0
                result = Math.max(result, dayMap[high][row][col])
            }
        }
    }

    if(result == 1_000_001){
        bw.append("-1")
    } else {
        bw.append("$result")
    }

    bw.flush()
    bw.close()
    
}

fun bfs(){

    while(!dq.isEmpty()){
        val now = dq.pollFirst()
        
        for(high in highArr){
            val nextHigh = now.high + high

            if(nextHigh < 0 || nextHigh >= mapHigh) continue
            if(map[nextHigh][now.row][now.col] == -1) continue
            if(dayMap[nextHigh][now.row][now.col] <= dayMap[now.high][now.row][now.col]+1) continue

            dayMap[nextHigh][now.row][now.col] = now.time+1
            dq.addLast(Info(nextHigh, now.row, now.col, now.time+1))
        }
        
        for(i in 0 .. 3){
            val nextRow = now.row + rowArr[i]
            val nextCol = now.col + colArr[i]

            if(nextRow < 0 || nextCol < 0 || nextRow >= mapRow || nextCol >= mapCol) continue
            if(map[now.high][nextRow][nextCol] == -1) continue
            if(dayMap[now.high][nextRow][nextCol] <= dayMap[now.high][now.row][now.col]+1) continue
            dayMap[now.high][nextRow][nextCol] = now.time+1
            dq.addLast(Info(now.high, nextRow, nextCol, now.time+1))
        }
        
    }
}