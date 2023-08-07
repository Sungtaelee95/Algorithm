import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

data class Info(val start:Int, val end: Int, val num: Int)

fun main(){

    var monthMap = HashMap<Int, Int>()

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())

    monthMap[1] = 0
    monthMap[2] = monthMap[1]!! + 31
    monthMap[3] = monthMap[2]!! + 28
    monthMap[4] = monthMap[3]!! + 31
    monthMap[5] = monthMap[4]!! + 30
    monthMap[6] = monthMap[5]!! + 31
    monthMap[7] = monthMap[6]!! + 30
    monthMap[8] = monthMap[7]!! + 31
    monthMap[9] = monthMap[8]!! + 31
    monthMap[10] = monthMap[9]!! + 30
    monthMap[11] = monthMap[10]!! + 31
    monthMap[12] = monthMap[11]!! + 30

    val min = monthMap[3]!! + 1
    val max = monthMap[12]!! + 1

    val N = st.nextToken().toInt()
    val dayFlower = IntArray(366){0}

    var flowerList = mutableListOf<Info>()
    var dayInFlower = mutableListOf<Info>()

    for(i in 1 .. N){
        st = StringTokenizer(br.readLine())
        val startDay = monthMap[st.nextToken().toInt()]!! + st.nextToken().toInt()
        val endDay = monthMap[st.nextToken().toInt()]!! + st.nextToken().toInt()

        if(endDay < min || startDay > max) continue
        if(startDay > endDay) continue

        if(min <= startDay && endDay <= max){
            dayInFlower.add(Info(startDay, endDay, i))
        }

        flowerList.add(Info(startDay, endDay, i))
    }

    var count = 365

    dayInFlower.sortWith( kotlin.Comparator { o1, o2 -> (o1.end - o1.start).compareTo(o2.end - o2.start)})

    for(i in 0 .. dayInFlower.size-1){
        val flower = dayInFlower[i]
        if(!dayFlower.slice(min .. max).contains(0)){
            count = Math.min(count, dayFlower.slice(min .. max).toSet().size)
        }
        for(j in flower.start .. flower.end){
            dayFlower[j] = flower.num
        }
    }

    if(!dayFlower.slice(min .. max).contains(0)){
        count = Math.min(count, dayFlower.slice(min .. max).toSet().size)
    }

    if(count == 365){
        flowerList.sortWith( kotlin.Comparator { o1, o2 -> (o1.end - o1.start).compareTo(o2.end - o2.start)})

        for(i in 0 .. flowerList.size-1){
            val flower = flowerList[i]
            if(!dayFlower.slice(min .. max).contains(0)){
                count = Math.min(count, dayFlower.slice(min .. max).toSet().size)
            }
            for(j in flower.start .. flower.end){
                dayFlower[j] = flower.num
            }
        }
        if(!dayFlower.slice(min .. max).contains(0)){
            count = Math.min(count, dayFlower.slice(min .. max).toSet().size)
        }
    }

    if(count == 365) count = 0

    bw.append("$count")
    //bw.appendLine(dayFlower.slice(min .. max).toSet().toString())
    bw.flush()
    bw.close()
}