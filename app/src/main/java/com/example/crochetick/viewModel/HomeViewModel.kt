package com.example.crochetick.viewModel

import androidx.lifecycle.ViewModel
import com.example.crochetick.repositories.CrochetickRepository

class HomeViewModel:ViewModel() {
    fun observeAllProjects() = CrochetickRepository.instance.getAllProjects()
}