package com.aangles.cmestas.myquispeyn.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aangles.cmestas.myquispeyn.repository.DataStorePreferenceRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DataStoreViewModel2 (
    private val dataStorePreferenceRespository: DataStorePreferenceRepository
): ViewModel(){

    private val _professionalSchool = MutableLiveData("")
    val professionalSchool: LiveData<String> = _professionalSchool

    init {
        viewModelScope.launch {
            dataStorePreferenceRespository.getProfessionalSchool.collect {
                _professionalSchool.value = it
            }
        }
    }

    suspend fun saveProfessionalSchool(_professionalSchool: String){
        dataStorePreferenceRespository.setProfessionalSchool(_professionalSchool)
    }
}