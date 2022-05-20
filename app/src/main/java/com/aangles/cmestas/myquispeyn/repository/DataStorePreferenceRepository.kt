package com.aangles.cmestas.myquispeyn.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStorePreferenceRepository (context: Context) {
    private val dataStore: DataStore<Preferences> = context.createDataStore(name = "SampleData")

    private val userNameDafault = ""
    private val professionalSchoolDefault = ""
    private val subjectCodeDefault = ""
    private val nameSubjectDefault = ""
    private val semesterDefault = ""
    private val durationDefault = ""

    companion object{
        val PREF_USERNAME = preferencesKey<String>("user_name")
        val PREF_PROFESSIONALSCHOOL = preferencesKey<String>("professional_school")
        val PREF_SUBJECTCODE = preferencesKey<String>("subject_code")
        val PREF_NAMESUBJECT = preferencesKey<String>("name_subject")
        val PREF_SEMESTER = preferencesKey<String>("semester")
        val PREF_DURATION = preferencesKey<String>("duration")

        private var INSTANCE: DataStorePreferenceRepository ?= null

        fun getInstance(context: Context): DataStorePreferenceRepository{
            return INSTANCE?: synchronized(this){
                INSTANCE?.let {
                    return it
                }
                val instance = DataStorePreferenceRepository(context)
                INSTANCE = instance
                instance
            }
        }

    }

    // set value
    suspend fun setUserName(_userName: String){
        dataStore.edit { preferences ->
            preferences[PREF_USERNAME] = _userName
        }
    }

    suspend fun setProfessionalSchool(_professionalSchool: String){
        dataStore.edit { preferences ->
            preferences[PREF_PROFESSIONALSCHOOL] = _professionalSchool
        }
    }

    // get value
    val getUserName: Flow<String> = dataStore.data
        .map { preferences->
            preferences[PREF_USERNAME] ?: userNameDafault
        }

    val getProfessionalSchool: Flow<String> = dataStore.data
        .map { preferences->
            preferences[PREF_PROFESSIONALSCHOOL] ?: professionalSchoolDefault
        }



}