package com.example.crochetick.state

import com.example.crochetick.dataClass.requestData.CategoriesResponse
import com.example.crochetick.dataClass.requestData.SchemesResponse

data class SchemesState(
    val categoryId:Int = 0,
    val categoryTitle:String="",
    val schemeId:Int=0,
    val createdId:Long=-1,
    val createdTitle:String="Деталь",
    val categories:List<CategoriesResponse> = listOf(),
    val schemes:List<SchemesResponse> = listOf(),
    val updated:Boolean=false
)
