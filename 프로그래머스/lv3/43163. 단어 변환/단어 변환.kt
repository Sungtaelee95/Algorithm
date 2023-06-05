class Solution {
    var changeCount : Int = 51
    fun solution(begin: String, target: String, words: Array<String>): Int {
        var answer = 0
        var visted = mutableMapOf<String,Boolean>()
        var vistedMap = mutableMapOf<String,MutableList<String>>() // 단어별 변환 될 수 있는 단어들
        if(!words.contains(target)) {
            return 0
        }
        var tempList = mutableListOf<String>()
        visted.put(begin,false)
        words.forEach{
            var count = 0
            for(i in 0 .. it.length-1){
                if(begin[i] == it[i]) count++
            }
            if(count == it.length-1){
                tempList.add(it)
            }
            visted.put(it,true)
        }
        vistedMap.put(begin,tempList)

        for(i in 0 .. words.size-1){
            var checkList = mutableListOf<String>()
            for(j in 0 .. words.size-1){
                if(i == j){
                    continue
                } else{
                    var count = 0
                    for(k in 0 .. words[i].length-1){
                        if(words[i][k] == words[j][k]) count++
                    }
                    if(count == words[i].length-1){
                        checkList.add(words[j])
                    }
                }
            }
            vistedMap.put(words[i],checkList)
        }
        // println(vistedMap)
        // println(visted)

        for(i in 0 until vistedMap[begin]!!.size){
            dfs(vistedMap[begin]!![i],visted,vistedMap,1,target)
        }

        return changeCount
    }

    fun dfs(begin: String, visted: MutableMap<String,Boolean>, 
            vistedMap: MutableMap<String,MutableList<String>>, 
            count: Int, target:String){
        
        var checkvisted = visted
        checkvisted.put(begin,false) // 방문표시
        // println(begin)
        // println(visted)
        if(begin == target){
            if(changeCount > count){
                changeCount = count
            }
            return
        }
        var checkList = vistedMap.get(begin)
        if(checkList!!.isEmpty()) return

        for(i in 0 .. checkList.size-1){
            if(checkvisted[checkList[i]]!!){
                dfs(checkList[i],checkvisted,vistedMap,count+1,target)
                checkvisted[checkList[i]] = true
            }
        }


    }

}