package com.elsloude.footballapi.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.elsloude.footballapi.presentation.ViewModelResponse
import java.lang.IllegalArgumentException

class ModelFactory(private val repo: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelResponse::class.java)) {
            return ViewModelResponse(repo) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}