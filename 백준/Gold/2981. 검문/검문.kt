import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*


fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val input = IntArray(n)
    repeat(n) {
        input[it] = br.readLine().toInt()
    }

    input.sort()
    var gcdNum = input[1] - input[0];
    for (i in 1 until n) {
        gcdNum = gcd(input[i] - input[i - 1], gcdNum)
    }
    for (i in 2..gcdNum) {
        if (gcdNum % i == 0) bw.append("$i ")
    }

    bw.flush()
    bw.close()
}

fun gcd(num1: Int, num2: Int): Int {
    if (num2 == 0) {
        return num1
    } else {
        return gcd(num2, num1 % num2)
    }

}