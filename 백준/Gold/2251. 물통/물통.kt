import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

var start = intArrayOf(0,0,1,1,2,2)
var end = intArrayOf(1,2,0,2,0,1)
lateinit var arr: IntArray // 물의 양 저장 목적
var answer = BooleanArray(400) // 기본 false 결과값 저장 배열(C의 양에 해당하는 인덱스 true 처리)
var visit = Array(400,{BooleanArray(400)}) // a와b의 물의 양에 따른 방문체크

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    arr = intArrayOf(st.nextToken().toInt(),st.nextToken().toInt(),st.nextToken().toInt())

    bfs()

    for(i in 0 .. answer.size-1){
        if(answer[i]) bw.append("$i ")
    }

    bw.flush()
    bw.close()

}

fun bfs(){
    visit[0][0] = true
    answer[arr[2]] = true

    var dq = ArrayDeque<Info>() // a와b의 물의 양 저장
    dq.add(Info(0,0))

    while(!dq.isEmpty()){
        val now = dq.poll()
        val a = now.A
        val b = now.B
        val c = arr[2]-a-b

        for(i in 0 .. 5){
            var temp = intArrayOf(a,b,c)
            temp[end[i]] += temp[start[i]]
            temp[start[i]] = 0
            if(temp[end[i]] > arr[end[i]]){
                temp[start[i]] = temp[end[i]] - arr[end[i]]
                temp[end[i]] = arr[end[i]]
            }
            if(!visit[temp[0]][temp[1]]){
                visit[temp[0]][temp[1]] = true
                dq.add(Info(temp[0],temp[1]))
                if(temp[0].equals(0)){
                    answer[temp[2]] = true
                }
            }
        }

    }

}

data class Info(
    val A: Int,
    val B: Int
)

