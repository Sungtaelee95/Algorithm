import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

lateinit var friend: IntArray
lateinit var money: IntArray

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    //val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    val k = st.nextToken().toInt() // 가지고 있는 돈

    friend = IntArray(n+1){i: Int -> i }
    money = IntArray(n+1){0}
    st = StringTokenizer(br.readLine())
    for(i in 1 .. n){
        money[i] = st.nextToken().toInt()
    }

    for(i in 1 .. m){
        st = StringTokenizer(br.readLine())
        var fr1 = st.nextToken().toInt()
        var fr2 = st.nextToken().toInt()
        union(fr1,fr2)
    }

    var sum = 0
    for(i in friend){
        if(0 != find(i)){
            sum += money[find(i)]
            union(0,i)
        }
    }

    if(sum > k) println("Oh no")
    else println(sum)

    //bw.flush()
    //bw.close()
}

fun find(a:Int):Int{
    if(friend[a] == a) return a
    friend[a] = find(friend[a])
    return friend[a]
}

fun union(a:Int,b:Int){
    var x = find(a)
    var y = find(b)
    if(x != y){
        if(money[x] <= money[y]){
            friend[y] = x
        } else {
            friend[x] = y
        }
    }
}
