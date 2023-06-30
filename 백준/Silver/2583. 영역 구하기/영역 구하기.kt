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


data class Info(val row: Int, val col: Int)

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var st = StringTokenizer(br.readLine())

    val mapRow = st.nextToken().toInt()
    val mapCol = st.nextToken().toInt()

    val map = Array(mapRow) {BooleanArray(mapCol)}

    val nr = intArrayOf(0, 0, 1, -1)
    val nc = intArrayOf(1, -1, 0, 0)
    val dq = ArrayDeque<Info>()

    repeat(st.nextToken().toInt()){
        st = StringTokenizer(br.readLine())
        val sCol = st.nextToken().toInt() // 4
        val sRow = st.nextToken().toInt() // 0
        val eCol = st.nextToken().toInt()-1
        val eRow = st.nextToken().toInt()-1
        

        for(row in sRow .. eRow){
            for(col in sCol .. eCol){
                map[row][col] = true
            }
        }
    }

    var count = 0
    var result = ArrayList<Int>()

    for(row in 0 .. map.size-1){
        for(col in 0 .. map[row].size-1){
            if(map[row][col]) continue
            count++
            dq.add(Info(row, col))
            var area = 1

            while(!dq.isEmpty()){
                val now = dq.pollFirst()
                map[now.row][now.col] = true

                for(i in 0 .. 3){
                    val nextRow = now.row + nr[i]
                    val nextCol = now.col + nc[i]

                    if(nextRow < 0 || nextCol < 0 || nextRow >= mapRow || nextCol >= mapCol) continue
                    if(map[nextRow][nextCol]) continue

                    map[nextRow][nextCol] = true
                    area++
                    dq.addLast(Info(nextRow, nextCol))
                }
            }
            result.add(area)
        }
    }

    bw.appendLine("$count")
    result.sorted().forEach {
        bw.append("$it ")
    }

    bw.flush()
    bw.close()
}

