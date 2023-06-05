import java.util.StringTokenizer

fun main() {
    var st = StringTokenizer(readln())
    val N = st.nextToken().toInt()
    var idx1 = 1
    var idx2 = 2
    var answer = 1
    var sum = idx1 + idx2
//    val s = System.currentTimeMillis()
    while(idx2 <= (N+1)/2 ){
        if(sum == N){
            answer++
            idx2++
            sum += idx2
        } else if(sum > N){
            sum -= idx1
            idx1++
        } else {
            idx2++
            sum += idx2
        }
    }
//    val e = System.currentTimeMillis()
    println(answer)
//    println((e-s)/1000.0)
}



