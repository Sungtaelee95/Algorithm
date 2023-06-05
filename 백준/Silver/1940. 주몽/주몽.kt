import java.util.StringTokenizer

fun main() {
    var st = StringTokenizer(readln())
    val N = st.nextToken().toInt() // 재료의 개수
    st = StringTokenizer(readln())
    val M = st.nextToken().toInt() // 갑옷을 만드는데 필요한 수

    var arr = IntArray(N + 1){0}
    st = StringTokenizer(readln())
    for(i in 1 .. N){
        arr[i] = st.nextToken().toInt()
    }

    arr.sort()

    var idx1 = 1
    var idx2 = N
    var result = 0
    while(idx1 < idx2){
        if(arr[idx1] + arr[idx2] == M){
            result++
            idx1++
            idx2--
        } else if(arr[idx1] + arr[idx2] < M){
            idx1++
        } else {
            idx2--
        }
    }
    println(result)
}


