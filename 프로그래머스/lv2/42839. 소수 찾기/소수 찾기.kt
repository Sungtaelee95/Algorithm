class Solution {
    var list = ArrayList<Int>()
    var number : Int = 0
    var numbers = ""
    lateinit var visit : BooleanArray
    lateinit var check : IntArray
    fun solution(numbers: String): Int {
        var answer = 0
        number = numbers.toCharArray().sortedDescending().joinToString("").toInt()
        visit = BooleanArray(numbers.length){true}
        this.numbers = numbers
        check = IntArray(number+1){i: Int -> i}
        for(i in 2 .. Math.sqrt(check.size.toDouble()).toInt()){
            var start = i
            if(check[start] == 0) continue
            while(start+i < check.size){
                start+=i
                check[start] = 0
            }
        }

        for(i in 0 .. numbers.length-1){
            visit[i] = false
            join(numbers[i].toString())
            visit[i] = true
        }

        return list.distinct().size
    }
    fun join(s:String){
        if(check[s.toInt()] != 0 && s.toInt() != 1){
            list.add(s.toInt())
        }
        for(i in 0 .. numbers.length-1){
            if(visit[i]){
                visit[i] = false
                join(s+numbers[i].toString())
                visit[i] = true
            }
        }
    }
}