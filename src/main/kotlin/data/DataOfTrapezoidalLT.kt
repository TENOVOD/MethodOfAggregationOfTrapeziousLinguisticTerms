package data

import model.AlternativeAndCriteriaCell
import screens.elements.linguistic_term.parseTermValueToFloatArray

data class TrapezoidalCell(
    val alternativeNumber:Int,
    val criteriaNumber: Int,
    val cellValue: Array<Float>
){
    companion object{
        fun getCellByAlternativeAndCriteriaNumber(alternativeNumber: Int, criteriaNumber: Int, listOfAlternativeAndCriteriaCells:List<TrapezoidalCell>): TrapezoidalCell {
            val result =  listOfAlternativeAndCriteriaCells.find { entry->
                entry.alternativeNumber==alternativeNumber && entry.criteriaNumber==criteriaNumber

            }
            if(result!=null)return result else return TrapezoidalCell(alternativeNumber,criteriaNumber, arrayOf())

        }
    }
}


var GLOBAL_MATRIX_OF_TRAPEZOIDAL_LT = listOf<TrapezoidalCell>()

fun transformInternalToTrapezoidalMatrix(listOfInternalMatrix:List<AlternativeAndCriteriaCell>): MutableList<TrapezoidalCell> {

    val listOfMatrixLinguisticTerm  = GLOBAL_LIST_OF_LINGUISTIC_TERMS
    val maxX = foundMaxValueOfTerms(listOfMatrixLinguisticTerm)

    val listOfTrapezoidalCells = mutableListOf<TrapezoidalCell>()

    listOfInternalMatrix.forEach {cell->

        val cellValue = cell.cellValue
            .replace("{","")
            .replace("}","")
            .trim()
        if(cellValue.length<=3){

            val term = listOfMatrixLinguisticTerm.find {
                it.shortName.uppercase() == cellValue
            }
            println("Cell value: $cellValue term limits: ${term?.limits}")
            val termLimitsArray = parseTermValueToFloatArray(term!!.limits)

            val trapezoidPoints = arrayOf(
                "%.2f".format((termLimitsArray[0]/maxX)).replace(',','.').toFloat(),
                "%.2f".format((termLimitsArray[1]/maxX)).replace(',','.').toFloat(),
                "%.2f".format((termLimitsArray[1]/maxX)).replace(',','.').toFloat(),
                "%.2f".format((termLimitsArray[2]/maxX)).replace(',','.').toFloat(),
            )

            val trapezoidalCell = TrapezoidalCell(cell.alternativeNumber,cell.criteriaNumber,trapezoidPoints)
            listOfTrapezoidalCells.add(trapezoidalCell)

        }else{
            var firstTermName = ""
            var secondTermName = ""

            for(i in cellValue.indices){
                if(cellValue[i]!=' '){
                    firstTermName+=cellValue[i]
                }else break
            }

            for(i in cellValue.length-1 downTo 0){
                if(cellValue[i]!=' '){
                    secondTermName+=cellValue[i]
                }else{
                    secondTermName = secondTermName.reversed()
                    break
                }
            }
            val firstTerm = listOfMatrixLinguisticTerm.find { term ->
                term.shortName.uppercase() ==firstTermName
            }
            println("CellVal: $cellValue FirstTermName: $firstTermName term limits: ${firstTerm?.limits}")

            val secondTerm = listOfMatrixLinguisticTerm.find { term ->
                term.shortName.uppercase() ==secondTermName
            }
            println("CellVal: $cellValue SecondTermName: $secondTermName term limits: ${secondTerm?.limits}")

            val firstTermLimitsArray = parseTermValueToFloatArray(firstTerm!!.limits)
            val secondTermLimitsArray = parseTermValueToFloatArray(secondTerm!!.limits)
            val trapezoidalPoints = arrayOf(
                "%.2f".format((firstTermLimitsArray[0]/maxX)).replace(',','.').toFloat(),
                "%.2f".format((firstTermLimitsArray[1]/maxX)).replace(',','.').toFloat(),
                "%.2f".format((secondTermLimitsArray[1]/maxX)).replace(',','.').toFloat(),
                "%.2f".format((secondTermLimitsArray[2]/maxX)).replace(',','.').toFloat()
            )
            val trapezoidalCell = TrapezoidalCell(cell.alternativeNumber, cell.criteriaNumber, trapezoidalPoints)
            listOfTrapezoidalCells.add(trapezoidalCell)
        }

    }
    return listOfTrapezoidalCells
}

fun foundMaxValueOfTerms(list:MutableList<LinguisticTerm>): Float {
    var maxValue = 1f
    list.forEach {
        val arrayOfLimits = parseTermValueToFloatArray(it.limits)
        if(arrayOfLimits[0]>maxValue)maxValue=arrayOfLimits[0]
        if(arrayOfLimits[1]>maxValue)maxValue=arrayOfLimits[1]
        if(arrayOfLimits[2]>maxValue)maxValue=arrayOfLimits[2]
    }
    return maxValue
}