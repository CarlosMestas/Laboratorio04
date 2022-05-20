package cmestas.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DataStoreViewModel2(
    private val dataStorePreferenceRespository: cmestas.repository.DataStorePreferenceRepository
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