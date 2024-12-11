package com.example.crochetick.state

import com.example.crochetick.dataClass.model.DetailDBTable

data class ProjectDoState(
    val projectId:Long=-1,
    val projectTitle:String="",
    val detailId:Long=-1,
    val currentDetail:DetailDBTable? =  null,
    val details: List<DetailDBTable> = listOf(),
    val countDetails:Int=-1,
    val countRow:Int=-1,
    val doneDetails:Int=-1,
    val doneRows:Int=-1,
)