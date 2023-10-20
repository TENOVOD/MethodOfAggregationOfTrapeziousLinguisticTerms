package model

data class AlternativeAndCriteriaCell(
    val alternativeNumber: Int,
    val criteriaNumber: Int,
    var cellValue : String ,
){
    val onNewCellValue: (String)-> Unit = {
        cellValue=it
    }

    companion object{
        fun getCellByAlternativeAndCriteriaNumber(alternativeNumber: Int, criteriaNumber: Int, listOfAlternativeAndCriteriaCells:List<AlternativeAndCriteriaCell>): AlternativeAndCriteriaCell {
            val result =  listOfAlternativeAndCriteriaCells.find { entry->
                entry.alternativeNumber==alternativeNumber && entry.criteriaNumber==criteriaNumber

            }
            if(result!=null)return result else return AlternativeAndCriteriaCell(alternativeNumber,criteriaNumber,"")

        }
    }


}
