package com.example.crochetick.state

import com.example.crochetick.dataClass.model.DetailDBTable
import kotlinx.coroutines.flow.Flow

data class ProjectDoState(
    val projectId:Long=-1,
    val detailId:Long=-1,
    //val detailIndexInList:Int=-1,
    val currentDetail:DetailDBTable? =  null,
    val details: List<DetailDBTable> = listOf()
)