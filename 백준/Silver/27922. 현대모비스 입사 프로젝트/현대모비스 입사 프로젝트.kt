import java.io.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    //ab,ac,bc,
    val (n,k) = br.readLine().split(" ").map{ it.toInt() }
    val movies = Array(3){
        mutableListOf<Movie>()
    }
    repeat(n) {
        val (a,b,c) = br.readLine().split(" ").map {it.toInt()}
        movies[0].add(Movie(a,b))
        movies[1].add(Movie(a,c))
        movies[2].add(Movie(b,c))
    }
    val sum1 = movies[0].sortedBy {it.x + it.y}.reversed().slice(0 until k).sumOf{it.x + it.y}
    val sum2 = movies[1].sortedBy {it.x + it.y}.reversed().slice(0 until k).sumOf{it.x + it.y}
    val sum3 = movies[2].sortedBy {it.x + it.y}.reversed().slice(0 until k).sumOf{it.x + it.y}

    bw.append("${maxOf(sum1, maxOf(sum2, sum3))}")

    bw.flush()
    bw.close()
}

data class Movie(val x: Int, val y: Int)