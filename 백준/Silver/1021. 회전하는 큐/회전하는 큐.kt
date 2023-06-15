import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.ArrayDeque
import java.util.StringTokenizer
fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())

    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()

    var dq = ArrayDeque<Int>()

    repeat(N){
        dq.add(it+1)
    }

    st = StringTokenizer(br.readLine())
    var count = 0
    repeat(M){
        val target = st.nextToken().toInt()


        if(dq.peek() == target){
            dq.pollFirst()

        } else {
            // 양쪽방향으로 돌리면서 비교할 dq 생성
            var frontPoll = ArrayDeque<Int>()
            var backPoll = ArrayDeque<Int>()

            // 양쪽방향으로 돌리면서 비교할 dq 셋팅
            while(!dq.isEmpty()){
                val data = dq.poll()
                frontPoll.add(data)
                backPoll.add(data)
            }

            // 2번 과정
            var count1 = 0
            while(frontPoll.peek() != target){
                frontPoll.addLast(frontPoll.pollFirst())
                count1++
            }

            // 3번 과정
            var count2 = 0
            while(backPoll.peek() != target){
                backPoll.addFirst(backPoll.pollLast())
                count2++
            }

            // 2번, 3번 과정 체크
            if(count1 < count2){
                count += count1
                frontPoll.pollFirst()
                dq = frontPoll
            } else {
                count += count2
                backPoll.pollFirst()
                dq = backPoll
            }

        }

    }

    bw.append("$count")


    bw.flush()
    bw.close()
}