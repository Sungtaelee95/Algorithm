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


data class Info(val high: Int, val row: Int, val col: Int)

lateinit var start : Info
lateinit var end : Info

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var st = StringTokenizer(br.readLine())

    val highArr = intArrayOf(-1, 1)
    val rowArr = intArrayOf(0, 0, -1, 1)
    val colArr = intArrayOf(-1, 1, 0, 0)

    while(true){

        val mapHigh = st.nextToken().toInt()
        val mapRow = st.nextToken().toInt()
        val mapCol = st.nextToken().toInt()
        if(mapHigh + mapRow + mapCol == 0) break

        val map = Array(mapHigh){Array(mapRow){CharArray(mapCol)}}
        val rootMap = Array(mapHigh){Array(mapRow){IntArray(mapCol){27_001}}}

        for (high in 0 until  mapHigh){
            for (row in 0 until mapRow){
                val floor = br.readLine()
                for (col in 0 until mapCol){
                    map[high][row][col] = floor[col]
                    if(map[high][row][col] == 'S') {
                        start = Info(high, row, col)
                        rootMap[high][row][col] = 0
                    }
                    if(map[high][row][col] == 'E') end = Info(high, row, col)
                }
            }
            br.readLine()
        }

        val dq = ArrayDeque<Info>()
        dq.add(start)

        while(!dq.isEmpty()){
            val now = dq.pollFirst()

            if (now == end){
                bw.appendLine("Escaped in ${rootMap[now.high][now.row][now.col]} minute(s).")
                break
            }

            for (high in highArr){
                val nextHigh = now.high + high

                if(nextHigh < 0 || nextHigh >= mapHigh) continue
                if(map[nextHigh][now.row][now.col] == '#') continue
                if(rootMap[nextHigh][now.row][now.col] <= rootMap[now.high][now.row][now.col]+1) continue

                rootMap[nextHigh][now.row][now.col] = rootMap[now.high][now.row][now.col]+1
                dq.addLast(Info(nextHigh, now.row, now.col))
            }

            for (i in 0 .. 3){
                val nextRow = now.row + rowArr[i]
                val nextCol = now.col + colArr[i]

                if(nextRow < 0 || nextCol < 0 || nextRow >= mapRow || nextCol >= mapCol) continue
                if(map[now.high][nextRow][nextCol] == '#') continue
                if(rootMap[now.high][nextRow][nextCol] <= rootMap[now.high][now.row][now.col]+1) continue

                rootMap[now.high][nextRow][nextCol] = rootMap[now.high][now.row][now.col]+1
                dq.addLast(Info(now.high, nextRow, nextCol))

            }
        }

        if(rootMap[end.high][end.row][end.col] == 27_001) bw.appendLine("Trapped!")

        st = StringTokenizer(br.readLine())

    }

    bw.flush()
    bw.close()

}