import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayList

data class Info(val day:Int, val price: Long)

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())

    val T = st.nextToken().toInt()

    repeat(T){
        st = StringTokenizer(br.readLine())
        val N = st.nextToken().toInt()
        st = StringTokenizer(br.readLine())
        val dayPrice = LongArray(N){st.nextToken().toLong()}

        var maxDays = ArrayList<Info>()

        for(i in 0 .. dayPrice.size-1){
            maxDays.add(Info(i, dayPrice[i]))
        }

        maxDays.sortWith( kotlin.Comparator { o1, o2 -> if (o1.price == o2.price) o1.day.compareTo(o2.day) else o2.price.compareTo(o1.price) })
        var maxDay = maxDays[0]
        var day = 0


        var result = 0L
        for(i in 0 .. dayPrice.size-1){
            if(dayPrice[i] < maxDay.price && i < maxDay.day){
                result += maxDay.price - dayPrice[i]
                continue
            }

            if(maxDay.day <= i && day < dayPrice.size-1) {
                day++
                maxDay = maxDays[day]
                while(maxDay.day <= i && day < dayPrice.size-1){
                    day++
                    maxDay = maxDays[day]
                }
            }
        }

        bw.appendLine("$result")
    }


    bw.flush()
    bw.close()
}