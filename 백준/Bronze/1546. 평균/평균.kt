import java.util.Scanner
import kotlin.math.max

fun main() {
    val sc = Scanner(System.`in`)
    val N = sc.nextInt()
    var scoreArray = IntArray(N){0}
    var max = 0L
    var sum = 0L
    for(i in 0 .. N-1){
        scoreArray[i] = sc.nextInt()
        sum += scoreArray[i].toLong()
        max = max(scoreArray[i].toLong(),max)
    }

    print(sum*100.0/max/N)
}