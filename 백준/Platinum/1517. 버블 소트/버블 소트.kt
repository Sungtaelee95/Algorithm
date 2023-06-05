import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Long.compare
import java.util.*
import kotlin.Comparator

lateinit var arr : IntArray
lateinit var temp : IntArray
var result : Long = 0L

fun main() {
    //val s = System.currentTimeMillis()
    var br = BufferedReader(InputStreamReader(System.`in`))
    //var bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt() // 자료의 개수
    //var sb = StringBuilder()
    arr = IntArray(N+1)
    temp = IntArray(N+1)
    st = StringTokenizer(br.readLine())
    repeat(N){
        arr[it+1] = st.nextToken().toInt()
    }
    Swap(1,N)

    print(result)

//    bw.flush()
//    bw.close()
//
//   val e = System.currentTimeMillis()
//   println((e-s)/1000.0)

}

fun Swap(s: Int, e: Int){
    if(e-s < 1)return
    var mid = s + (e-s)/2

    Swap(s,mid)
    Swap(mid+1,e)

    for(i in s .. e){
        temp[i] = arr[i]
    }

    var index1 = s
    var index2 = mid + 1
    var check = s

    while(index1 <= mid && index2 <= e){
        if(temp[index1] > temp[index2]){
            arr[check] = temp[index2]
            result += (index2-check)
            index2++
            check++
        } else {
            arr[check] = temp[index1]
            index1++
            check++
        }
    }
    while(index1 <= mid){
        arr[check] = temp[index1]
        index1++
        check++
    }
    while(index2 <= e){
        arr[check] = temp[index2]
        check++
        index2++
    }


}
