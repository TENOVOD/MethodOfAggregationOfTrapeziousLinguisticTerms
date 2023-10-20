package data

import model.AlternativeAndCriteriaCell

data class LinguisticTerm(
    val fullName: String,
    val shortName: String,
    val limits: String
)


val GLOBAL_LIST_OF_LINGUISTIC_TERMS = mutableListOf<LinguisticTerm>()

fun parseExpertEvaluationToIntervalEstimates(inputList:List<AlternativeAndCriteriaCell>):List<AlternativeAndCriteriaCell>{
    val listOfIntervalEstimates = mutableListOf<AlternativeAndCriteriaCell>()
    val listOfTermsShortName = mutableListOf<String>()


    GLOBAL_LIST_OF_LINGUISTIC_TERMS.forEach { term ->
        listOfTermsShortName.add(term.shortName.lowercase())

    }

    inputList.forEach {cell->
        val cellValue = cell.cellValue.trim().lowercase()

        //simple term like C or BC
        if(cellValue.length<3){
            val newCell = AlternativeAndCriteriaCell(cell.alternativeNumber,cell.criteriaNumber,"{ ${cellValue.uppercase()} }")
            listOfIntervalEstimates.add(newCell)
        }
        //upper , lower like вище C
        if(cellValue.contains("вище")||cellValue.contains("нижче")){
            if(cellValue.contains("вище")){
                val tempValue = cellValue.replace("вище","").trim()
                var newCellValue = ""
                var isCurrentTerm = false
                listOfTermsShortName.forEach { term->
                    if(term==tempValue){
                        isCurrentTerm=true
                    }
                    if(isCurrentTerm){
                        newCellValue+="$term "
                    }
                }

                val newCell = AlternativeAndCriteriaCell(cell.alternativeNumber,cell.criteriaNumber,"{ ${newCellValue.uppercase()}}")
                listOfIntervalEstimates.add(newCell)
            }else{
                val tempValue = cellValue.replace("нижче","").trim()
                println("ЦЕ без нижче: $tempValue")
                var newCellValue = ""
                var isCurrentTerm = false
                listOfTermsShortName.forEach { term->
                    if(term==tempValue){
                        isCurrentTerm=true
                        newCellValue+="$term "
                    }
                    if(!isCurrentTerm){
                        newCellValue+="$term "
                    }
                }

                val newCell = AlternativeAndCriteriaCell(cell.alternativeNumber,cell.criteriaNumber,"{ ${newCellValue.uppercase()}}")
                listOfIntervalEstimates.add(newCell)
            }
        }
        if(cellValue.contains("в межах")){
            val tempValue = cellValue.replace("в межах","").trim().replace("та","|")
            println("ЦЕ без в межах та : $tempValue")
            var firstTerm=""
            var secondTerm=""

            var isNextTerm = false

            tempValue.forEach { char->
                if(char!=' '&&!isNextTerm&&char!='|'){
                    firstTerm+=char
                }else if(char=='|'){
                    isNextTerm=true

                }else if (char!=' '){
                    secondTerm+=char
                }
            }

            var newCellValue = ""
            var isCurrentLimit=false
            listOfTermsShortName.forEach { term->
                if(term==firstTerm){
                    isCurrentLimit=true
                    newCellValue+="$term "
                }else if(term==secondTerm){
                    isCurrentLimit=false
                    newCellValue+="$term "
                }
                else if(isCurrentLimit){
                    newCellValue+="$term "
                }
            }
            val newCell = AlternativeAndCriteriaCell(cell.alternativeNumber,cell.criteriaNumber,"{ ${newCellValue.uppercase()}}")
            listOfIntervalEstimates.add(newCell)
        }

    }
    if(listOfIntervalEstimates.size == GLOBAL_MATRIX_OF_EXPERT_EVALUATION.size){
        GLOBAL_MATRIX_OF_INTERVAL_ESTIMATES = listOfIntervalEstimates
    }
    return listOfIntervalEstimates
}