import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.ArrayDeque
import java.util.ArrayList
import java.util.LinkedList
import java.util.Stack
import java.util.StringTokenizer

data class Smile(var displayCount: Int, var clipCount: Int, var time: Int)
fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val S = br.readLine().toInt()

    val dq = ArrayDeque<Smile>()
    dq.add(Smile(1,0,0))
    val countVisit = Array(2*S+1) {BooleanArray(2*S+1)}
    countVisit[1][0] = true

    while(!dq.isEmpty()){
        val now = dq.pollFirst()

        val nowDC = now.displayCount
        val nowCC = now.clipCount

        if(nowDC == S){
            bw.append("${now.time}")
            break
        }
        // 화면에 있는 이모티콘을 모두 복사해서 클립보드에 저장한다.
        if(nowDC > 0 && !countVisit[nowDC][nowDC]){
            countVisit[nowDC][nowDC] = true
            dq.addLast(Smile(nowDC, nowDC, now.time+1))
        }
        // 클립보드에 있는 모든 이모티콘을 화면에 붙여넣기 한다.
        if(nowCC > 0 && (nowDC + nowCC) < 2*S+1 && !countVisit[nowDC + nowCC][nowCC]){
            countVisit[nowDC + nowCC][nowCC] = true
            dq.addLast(Smile(nowDC + nowCC, nowCC, now.time+1))
        }
        // 화면에 있는 이모티콘 중 하나를 삭제한다.
        if(nowDC > 0 && !countVisit[nowDC-1][nowCC]){
            countVisit[nowDC-1][nowCC] = true
            dq.addLast(Smile(nowDC-1, nowCC, now.time+1))
        }

    }

    bw.flush()
    bw.close()
}

