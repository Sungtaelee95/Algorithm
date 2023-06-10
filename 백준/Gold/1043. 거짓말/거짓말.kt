import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

lateinit var human: IntArray
//val bw = BufferedWriter(OutputStreamWriter(System.out)) 테스트
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    //val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()+1 // 사람의 수
    val m = st.nextToken().toInt() // 파티의 수

    human = IntArray(n){i: Int ->  i} // 사람의 수
    st = StringTokenizer(br.readLine())
    val truecount = st.nextToken().toInt() // 진실을 아는 사람들의 수
    if(truecount == 0){
        println(m)
        return
    }
    val firsttrue = st.nextToken().toInt() // 추후 해당 번호를 가지고 있거나 혹은 변경 될 사람이 없는 파티 에서만 거짓말 가능
    for(i in 2 .. truecount){ // 알고 있는 사람들 중 번호가 가장 낮은 사람의 번호로 통일
        human[st.nextToken().toInt()] = firsttrue
    }

    var party = ArrayList<IntArray>() // 파티 별 참가 인원 저장 배열

    for(i in 1 .. m){
        st = StringTokenizer(br.readLine())
        val count = st.nextToken().toInt() // 파티 참석 인원
        var temparr = IntArray(count){_: Int -> st.nextToken().toInt()}
        party.add(temparr)
    }

    for(i in 0 .. party.size-1){ // 파티 별 참가 인원 진실을 아는 사람과 파티가 있는 지 확인
        if(party[i].size == 1) continue
        for(j in 0 .. party[i].size-2){
            union(party[i][j],party[i][j+1])
        }
    }

    var lieCount = 0

    for(i in 0 .. party.size-1){
        if(party[i].size == 1){
            if(find(party[i][0]) != find(firsttrue))lieCount++
            continue
        }
        var check = true
        for(j in 0 .. party[i].size-1){
            if(find(party[i][j]) == find(firsttrue)){
                check = false
                break
            }
        }
        if(check)lieCount++
    }
    println(lieCount)

    //bw.flush()
    //bw.close()
}

fun find(a:Int):Int{
    if(a == human[a]) return a
    else {
        human[a] = find(human[a])
    }
    return human[a]
}

fun union(a:Int,b:Int){
    val A = find(a)
    val B = find(b)
    if(A != B) {
        if (A < B) human[B] = A
        else human[A] = B
    }
}
