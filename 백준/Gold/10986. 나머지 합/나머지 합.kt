import java.util.StringTokenizer

fun main() {
    var st = StringTokenizer(readln())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val arr = LongArray(N+1){0}
    val arr2 = LongArray(M){0}

    var result : Long = 0

    st = StringTokenizer(readln())
    for(i in 1 .. N){
        arr[i] = st.nextToken().toInt() + arr[i-1]
        if(arr[i] % M == 0L) result++
        arr2[(arr[i] % M).toInt()]++
    }

    //var sb = StringBuilder()
    for(i in 0 until M){
        result += arr2[i]*(arr2[i]-1)/2
    }
    println(result)
}