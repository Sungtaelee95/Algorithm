import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()

    var bilding = IntArray(n+1){0} // 건물 별 선제 조건이 필요한 횟수
    var next = Array(n+1,{ArrayList<Int>()}) // 해당 건물을 지으면 지을 수 있는 건물들
    var timeList = IntArray(n+1){0} // 건물별 건설시간 저장 배열

    for(i in 1 .. n){
        st = StringTokenizer(br.readLine())
        var time = st.nextToken().toInt() // 해당 건물을 짓는 데 걸리는 시간
        timeList[i] = time
        while(true){
            var need = st.nextToken().toInt() // 선제 조건
            if(need == -1) break
            next[need].add(i)
            bilding[i]++
        }
    }

    var result = IntArray(n+1){0} // 결과값 저장
    var dq = ArrayDeque<Int>()
    for(i in 1 .. bilding.size-1){
        if(bilding[i] == 0){
            dq.add(i)
            result[i] = timeList[i]
        }
    }

    while(!dq.isEmpty()){
        val now = dq.pollFirst()

        for(nextbild in next[now]){
            bilding[nextbild]--
            result[nextbild] = Math.max(result[nextbild],result[now]+timeList[nextbild])
            if(bilding[nextbild]==0){
                dq.addLast(nextbild)
            }
        }

    }

    for(i in 1 .. result.size-1){
        bw.appendLine(result[i].toString())
    }

    bw.flush()
    bw.close()
}

data class Info(
    val bilding: Int,
    val time: Int
)