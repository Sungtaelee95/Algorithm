import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

lateinit var password : CharArray
var pw = IntArray(4){0} // 패스워드 조건
var pwCheck = IntArray(4){0} // 현재 문자열과 패스워드 비교용
var finalCheck = 0

fun main() {
    var br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val slen = st.nextToken().toInt() // 문자열 길이
    val pwlen = st.nextToken().toInt() // 만드려는 패스워드의 길이
    password = br.readLine().toCharArray() // 전체 패스워드

    st = StringTokenizer(br.readLine())
    repeat(4){
        pw[it] = st.nextToken().toInt()
    }

    pw.forEach {
        if(it == 0)finalCheck++
    }

    var result = 0
    repeat(pwlen){
        add(password[it])
    }
    if(finalCheck == 4) result++


    for(i in pwlen .. slen-1){
        var j = i - pwlen
        add(password[i])
        remove(password[j])
        if(finalCheck == 4) result++
    }

    println(result)

}

fun add(a: Char){
    when(a){
        'A' -> {pwCheck[0]++
            if (pwCheck[0] == pw[0]) {
                finalCheck++
            }
        }

        'C' -> {pwCheck[1]++
            if (pwCheck[1] == pw[1]) {
                finalCheck++
            }
        }

        'G' -> {pwCheck[2]++
            if (pwCheck[2] == pw[2]) {
                finalCheck++
            }
        }

        'T' -> {pwCheck[3]++
            if (pwCheck[3] == pw[3]) {
                finalCheck++
            }
        }
    }
}

fun remove(r: Char){
    when(r){
        'A' -> {
            if (pwCheck[0] == pw[0]) {
                finalCheck--
            }
            pwCheck[0]--
        }

        'C' -> {
            if (pwCheck[1] == pw[1]) {
                finalCheck--
            }
            pwCheck[1]--
        }

        'G' -> {
            if (pwCheck[2] == pw[2]) {
                finalCheck--
            }
            pwCheck[2]--
        }

        'T' -> {
            if (pwCheck[3] == pw[3]) {
                finalCheck--
            }
            pwCheck[3]--
        }
    }
}


