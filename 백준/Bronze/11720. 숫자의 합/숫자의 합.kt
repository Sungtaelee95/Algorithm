import java.util.Scanner

fun main() {
    val sc = Scanner(System.`in`)
    val N = sc.nextInt()
    val sNum = sc.next().toCharArray()
    var sum = 0
    sNum.forEach{
        sum += it.toString().toInt()
    }
    print(sum)
}