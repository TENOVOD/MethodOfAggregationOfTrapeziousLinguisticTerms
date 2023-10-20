package screens.elements.linguistic_term

fun parseTermValueToFloatArray(limits:String): List<Float> {

    var listOfTermsPoints = listOf<Float>()

    var startX = ""
    var midlX=""
    var endX=""


    var countPoints=0;
    limits.trim()
    limits.forEach { symbol->
        if(symbol!= ' '){
            when(countPoints){
                0->{startX+=symbol}
                1->{midlX+=symbol}
                2->{endX+=symbol}
            }
        }else{
            countPoints++
        }
    }
    listOfTermsPoints = listOf (startX.toFloat(),midlX.toFloat(),endX.toFloat())
    println(listOfTermsPoints[0])
    return listOfTermsPoints
}