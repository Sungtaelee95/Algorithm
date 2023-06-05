import java.util.*

fun main() {
//    val s = System.currentTimeMillis()
    var st = StringTokenizer(readln())
    val N = st.nextToken().toInt() // 수의 개수
    st = StringTokenizer(readln())

    var arr = IntArray(N){0}

    repeat(N){
        arr[it] = st.nextToken().toInt()
    }

    arr.sort()

    var count = 0

    for(i in 0 until N){
        var s = 0
        var e = N-1
        var check = arr[i]
        while(s < e){
            if(arr[s] + arr[e] == check){
                if(s != i && e != i){
                    count++
                    break
                } else if(s == i){
                    s++
                } else if(e == i) {
                    e--
                }
            } else {
                if(arr[s] + arr[e] < check){
                    s++
                } else {
                    e--
                }
            }
        }

    }

//    val e = System.currentTimeMillis()
    println(count)
//    println((e-s)/1000.0)

}


