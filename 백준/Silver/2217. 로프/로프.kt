import java.io.*

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    //var st = StringTokenizer(br.readLine())

    val N = br.readLine().toInt()
    var arr = IntArray(N){br.readLine().toInt()}.sortedDescending()


    var max = 0
    var temp = arr[0]
    var count = 0

    for(i in 0 .. arr.size-1){
        if(arr[i] == temp){
            count++
            continue
        } else {
            max = Math.max(max, count*temp)
            count++
            temp = arr[i]
            if(i == arr.size-1){
                max = Math.max(max, count*temp)
                count++
            }
        }
    }

    if(max == 0) max = arr[0]

    bw.append("$max")

    bw.flush()
    bw.close()
}