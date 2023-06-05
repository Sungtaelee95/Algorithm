import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Math.max
import java.util.*

val ar = intArrayOf(0,0,1,-1) // 상하좌우
val ac = intArrayOf(-1,1,0,0)

var N = 0
var M = 0

data class Info( // 로우, 컬럼
    val r: Int,
    val c: Int
)
val area = ArrayList<IntArray>() // 전체 맵
var visit = ArrayList<BooleanArray>() // 방문처리
var virus = mutableListOf<Info>() // 맵 내 바이러스 위치
var block = ArrayDeque<Info>() // 맵 내 추가로 기둥을 세울 위치

fun main() {
    //val s = System.currentTimeMillis()
    val br = BufferedReader(InputStreamReader(System.`in`))
    //var bw = BufferedWriter(OutputStreamWriter(System.out))

    var st = StringTokenizer(br.readLine())
    N = st.nextToken().toInt()
    M = st.nextToken().toInt()


    for(i in 0 .. N-1){ // 자료 입력
        var temp = IntArray(M){0}
        var temp2 = BooleanArray(M){true}
        st = StringTokenizer(br.readLine())
        for(j in 0 .. M-1){
            temp[j] = st.nextToken().toInt()
            if(temp[j] == 2){
                virus.add(Info(i,j))
            }
        }
        area.add(temp)
        visit.add(temp2)
    }

    var result = 0

    for(i in 0 .. N*M-3){
        if(area[i/M][i%M] != 0) continue
        block.add(Info(i/M,i%M))
        for(j in i+1 .. N*M-2){
            if(area[j/M][j%M] != 0) continue
            block.add(Info(j/M,j%M))
            for(k in j+1 .. N*M-1){
                if(area[k/M][k%M] != 0) continue
                block.add(Info(k/M,k%M))
                result = max(result,bfs(block)) // 새 기둥들의 정보 전달
                block.pollLast()
            }
            block.pollLast()
        }
        block.pollLast()
    }
    println(result)

//var sb = StringBuilder()
//    bw.flush()
//    bw.close()
//
//   val e = System.currentTimeMillis()
//   println((e-s)/1000.0)
}

fun bfs(data:ArrayDeque<Info>):Int{

    var check = Array(N,{IntArray(M,{0})})

    for(i in 0 .. area.size-1){
        for(j in 0 .. area[i].size-1){
            check[i][j] = area[i][j]
        }
    }


    for(node in data){ // 새로운 기둥 정보 입력
        check[node.r][node.c] = 1
    }


    var dq = ArrayDeque<Info>()
    for(i in 0 .. virus.size-1) {
        dq.add(virus[i])
    }

    while(!dq.isEmpty()){
        var now = dq.pollFirst()
        check[now.r][now.c] = 2

        for(j in 0 .. ar.size-1){
            val nr = now.r+ar[j]
            val nc = now.c+ac[j]

            if(nr < 0 || nc < 0 || nr >= N || nc >= M) continue
            if(check[nr][nc] != 0) continue
            check[nr][nc] = 2
            dq.addLast(Info(nr,nc))
        }

    }

    var count = 0

    for(i in 0 .. check.size-1){
        for(j in 0 .. check[i].size-1){
            if(check[i][j] == 0) count++
        }
    }

    return count
}


