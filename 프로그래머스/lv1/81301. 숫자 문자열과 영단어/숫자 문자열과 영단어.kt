class Solution {
    fun solution(s: String): Int {
        // one4seveneight
        val spel = listOf("zero","one","two","three","four","five","six","seven","eight","nine")
        
        var temp = s
        
        for(i in 0 .. spel.size-1){
            if(temp.contains(spel[i])){
                temp = temp.replace(spel[i],i.toString())
            }
        }
        
        return temp.toInt()
    }
}