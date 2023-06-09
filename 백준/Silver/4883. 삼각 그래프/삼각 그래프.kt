import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var case = 1

    while(true){
        var st = StringTokenizer(br.readLine())
        var N = st.nextToken().toInt()
        if(N == 0) break

        val map = Array(N) { LongArray(3) }
        val resultMap = Array(N) { LongArray(3)}
        // 확인할 배열 세팅
        repeat(N){
            st = StringTokenizer(br.readLine())
            map[it][0] = st.nextToken().toLong()
            map[it][1] = st.nextToken().toLong()
            map[it][2] = st.nextToken().toLong()
        }

        resultMap[0][1] = map[0][1]
        resultMap[0][2] = map[0][1] + map[0][2]

        for(row in 1 .. N-1){
            for(col in 0 .. 2){

                if(col == 0){
                    if(row == 1){
                        resultMap[row][col] = resultMap[0][1] + map[1][0]
                        continue
                    }
                    resultMap[row][0] = resultMap[row-1][0] + map[row][0]
                    resultMap[row][0] = Math.min(resultMap[row][0], resultMap[row-1][1] + map[row][0])
                }

                if(col == 1){

                    resultMap[row][1] = resultMap[row][0] + map[row][1] // 좌측에서 오는 거
                    resultMap[row][1] = Math.min(resultMap[row][1], resultMap[row-1][1] + map[row][1])
                    resultMap[row][1] = Math.min(resultMap[row][1], resultMap[row-1][2] + map[row][1])
                    if(row != 1) resultMap[row][1] = Math.min(resultMap[row][1], resultMap[row-1][0] + map[row][1])
                }

                if(col == 2){

                    resultMap[row][2] = resultMap[row][1] + map[row][2] // 좌측에서 오는 거
                    resultMap[row][2] = Math.min(resultMap[row][2], resultMap[row-1][1] + map[row][2])
                    resultMap[row][2] = Math.min(resultMap[row][2], resultMap[row-1][2] + map[row][2])
                }

            }
        }

        //bw.appendLine(resultMap.contentDeepToString())
        bw.appendLine("${case}. ${resultMap[N-1][1]}")

        case++

    }

    bw.flush()
    bw.close()
}
