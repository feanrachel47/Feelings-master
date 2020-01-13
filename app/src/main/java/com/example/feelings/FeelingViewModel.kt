package com.example.feelings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.feelings.FeelingDAO as feelingDAO

class FeelingViewModel(application: Application): AndroidViewModel(application) {
    private val repository:FeelingRepository
    val allFeeling: LiveData<List<Feeling>>

    init{

        //get a reference to the DAO
        val feelingDAO = FeelingDatabase.getDatabase(application).feelingDAO()

        repository = FeelingRepository(feelingDAO)
        allFeeling = repository.allFeeling
    }


    //viewmodel to use coroutine to perform long process
    fun insert(feeling: Feeling) = viewModelScope.launch{
        repository.insert(feeling)
    }
}