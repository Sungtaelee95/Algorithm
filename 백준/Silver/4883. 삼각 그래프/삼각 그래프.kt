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

//        resultMap[1][0] = resultMap[0][1] + map[1][0]
//        resultMap[1][1] = Math.min(resultMap[0][1] + map[1][1] , resultMap[0][2] + map[1][1])
//        resultMap[1][2] = Math.min(resultMap[0][1] + map[1][2] , resultMap[0][2] + map[1][2])


        for(row in 1 .. N-1){
            for(col in 0 .. 2){

                if(col == 0){
                    if(row == 1){
                        resultMap[row][col] = resultMap[row-1][col+1] + map[row][col]
                        continue
                    }
                    resultMap[row][col] = resultMap[row-1][col] + map[row][col]
                    resultMap[row][col] = Math.min(resultMap[row][col], resultMap[row-1][col+1] + map[row][col])
                }

                if(col == 1){

                    resultMap[row][col] = resultMap[row][0] + map[row][col] // 좌측에서 오는 거
                    resultMap[row][col] = Math.min(resultMap[row][col], resultMap[row-1][col] + map[row][col])
                    resultMap[row][col] = Math.min(resultMap[row][col], resultMap[row-1][2] + map[row][col])
                    if(row != 1) resultMap[row][col] = Math.min(resultMap[row][col], resultMap[row-1][0] + map[row][col])
                }

                if(col == 2){

                    resultMap[row][col] = resultMap[row][col-1] + map[row][col] // 좌측에서 오는 거
                    resultMap[row][col] = Math.min(resultMap[row][col], resultMap[row-1][col-1] + map[row][col])
                    resultMap[row][col] = Math.min(resultMap[row][col], resultMap[row-1][col] + map[row][col])
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
