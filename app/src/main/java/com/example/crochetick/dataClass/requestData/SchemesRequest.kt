package com.example.crochetick.dataClass.requestData

data class CategoriesResponse(
    val name:String,
    val id:Int,
)

data class SchemesResponse(
    val id: Int,
    val name: String,
    val image:String?,
    val description: String?,
    val category: CategoriesResponse,
    val details: List<DetailResponse>
)

data class DetailResponse(
    val id: Int,
    val name: String,
    val details_number: Int,
    val rows_number: Int,
    val schema_text: String?,
    val schema_image: String?
)