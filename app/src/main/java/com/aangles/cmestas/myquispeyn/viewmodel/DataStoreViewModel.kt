package com.aangles.cmestas.myquispeyn.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aangles.cmestas.myquispeyn.repository.DataStorePreferenceRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DataStoreViewModel (
    private val dataStorePreferenceRespository: DataStorePreferenceRepository
): ViewModel(){
    private val _userName = MutableLiveData("")
    val userName: LiveData<String> = _userName

    private val _professionalSchool = MutableLiveData("")
    val professionalSchool: LiveData<String> = _professionalSchool

    init {
        viewModelScope.launch {
            dataStorePreferenceRespository.getUserName.collect {
                _userName.value = it
            }
            dataStorePreferenceRespository.getProfessionalSchool.collect {
                _professionalSchool.value = it
            }
        }
    }

    suspend fun saveUserName(_userName: String){
        dataStorePreferenceRespository.setUserName(_userName)
    }

    suspend fun saveProfessionalSchool(_professionalSchool: String){
        dataStorePreferenceRespository.setProfessionalSchool(_professionalSchool)
    }
}

/*
package com.aangles.cmestas.myquispeyn.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aangles.cmestas.myquispeyn.repository.DataStorePreferenceRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DataStoreViewModel (
    private val dataStorePreferenceRespository: DataStorePreferenceRepository
): ViewModel(){
    private val _userName = MutableLiveData("")
    val userName: LiveData<String> = _userName


    init {
        viewModelScope.launch {
            dataStorePreferenceRespository.getUserName.collect {
                _userName.value = it
            }
        }
    }

    suspend fun saveUserName(_userName: String){
        dataStorePreferenceRespository.setUserName(_userName)
    }

}

**/