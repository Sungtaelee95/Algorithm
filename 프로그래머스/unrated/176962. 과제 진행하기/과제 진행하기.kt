import java.util.ArrayDeque
import java.util.PriorityQueue

class Solution {
    fun solution(plans: Array<Array<String>>): Array<String> {
        var answer: Array<String> = arrayOf<String>()
        val plan = plans.sortedWith( Comparator { o1, o2 -> o1[1].compareTo(o2[1])})
        var list = mutableListOf<Info>()
        for(i in 0 .. plan.size-1){
            val stu = plan[i][0]
            val stime = plan[i][1].split(":")[0].toInt()*60+plan[i][1].split(":")[1].toInt()
            val remain = plan[i][2].toInt()

            if(i != plan.size-1){
                val ntime = plan[i+1][1].split(":")[0].toInt()*60+plan[i+1][1].split(":")[1].toInt() // 다음과목 시작 시간
                if(stime+remain <= ntime){
                    answer += stu
                    var time = ntime - (stime+remain)
                    while(time > 0 && !list.isEmpty()){
                        if(time - list.last().remaining >= 0){
                            time -= list.last().remaining
                            answer += list.last().study
                            list.removeLast()
                            continue
                        }
                        if(time - list.last().remaining < 0){
                            list.last().remaining -= time
                            time = 0
                        }
                    }
                }
                if(stime+remain > ntime){
                    list.add(Info(stu,(stime+remain) - ntime))
                }
            } else {
                answer += stu
            }

        }

        while(!list.isEmpty()){
            answer += list.last().study
            list.removeLast()
        }

        return answer
    }

    data class Info(
        val study:String,
        var remaining :Int /* 남은 시간 */
    )
}