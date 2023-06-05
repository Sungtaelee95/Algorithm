import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

lateinit var city: IntArray
//val bw = BufferedWriter(OutputStreamWriter(System.out))
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    //val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    var n = st.nextToken().toInt()+1 // 도시 개수
    st = StringTokenizer(br.readLine())
    var m = st.nextToken().toInt() // 여행 경로 데이터
    city = IntArray(n){i: Int ->  i} // 도시 정보

    n--
    for(i in 1 ..n){
        st = StringTokenizer(br.readLine())
        for(j in 1 .. n){
            if(st.nextToken().toInt() == 1){
                union(i,j)
            }
        }
    }
    st = StringTokenizer(br.readLine())
    var start = IntArray(m){i: Int -> st.nextToken().toInt()}

    var check = true
    for(i in 0 .. start.size-2){
        if(city[start[i]] != city[start[i+1]]){
            check = false
            break
        }
    }

    if(check){
        println("YES")
    } else {
        println("NO")
    }

    //bw.flush()
    //bw.close()
}

fun union(a:Int,b:Int){
    val A = find(a)
    val B = find(b)

    if(A != B){
        if(A < B){
            city[B] = A
        } else {
            city[A] = B
        }
    }
}

fun find(a:Int):Int{
    if(city[a] == a) return a
    else {
        city[a] = find(city[a])
    }
    return city[a]
}
