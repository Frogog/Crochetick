package com.example.crochetick.repositories

import com.example.crochetick.provider.DBProvider

class CrochetickRepository {
    private val crochetickDao = DBProvider.instance.crochetickDao()

    companion object{
        val instance = CrochetickRepository()
    }

    fun getAllProjects() = crochetickDao.getAllProjects()

}