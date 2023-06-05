class Solution {
    var list = mutableListOf<String>()
    var arr = charArrayOf('A', 'E', 'I', 'O', 'U')
    fun solution(word: String): Int {
        for(i in 0 .. arr.size-1){
            text(arr[i].toString(),0)
        }
        return list.indexOf(word)+1
    }
    fun text(s: String,cnt: Int){
        if(cnt == arr.size) return
        list.add(s)
        for(i in 0 .. arr.size-1){
            text(s+arr[i].toString(),cnt+1)
        }

    }
}