package com.aangles.cmestas.myquispeyn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aangles.cmestas.myquispeyn.repository.DataStorePreferenceRepository
import com.aangles.cmestas.myquispeyn.ui.theme.Laboratorio04Theme
import com.aangles.cmestas.myquispeyn.viewmodel.DataStoreViewModel
import com.aangles.cmestas.myquispeyn.viewmodel.DataStoreViewModel2
import kotlinx.coroutines.launch
import java.lang.IllegalStateException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Laboratorio04Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DataStoreInput()
                }
            }
        }
    }
}

@Composable
fun DataStoreInput(
    viewModel: DataStoreViewModel = viewModel(
        factory = DataStoreViewModelFactory(DataStorePreferenceRepository(LocalContext.current))
    )
){
    val context = LocalContext.current
    val dataStorePreferenceRepository: DataStorePreferenceRepository = DataStorePreferenceRepository.getInstance(context)
    val textStatePeriodAcademic = remember {
        mutableStateOf(TextFieldValue())
    }
    val textStateProfessionalSchool = remember {
        mutableStateOf(TextFieldValue())
    }
    val textStateSubjectCode = remember {
        mutableStateOf(TextFieldValue())
    }
    val textStateNameSubject = remember {
        mutableStateOf(TextFieldValue())
    }
    val textStateSemester = remember {
        mutableStateOf(TextFieldValue())
    }
    val textStateDuration = remember {
        mutableStateOf(TextFieldValue())
    }

    val getUserName = viewModel.userName.observeAsState().value
    val getProfessionalSchool = viewModel.professionalSchool.observeAsState().value
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize()
    ){
        /*
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Purple500)
                .height(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            Text(
                text = "DataStore Preference",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
        */
        Column (
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(
                modifier = Modifier.height(30.dp)
            )
            OutlinedTextField(
                value = textStatePeriodAcademic.value,
                onValueChange = { textStatePeriodAcademic.value = it},
                label = {
                    Text(
                        text = "Ingrese el periodo académico",
                        fontSize = 15.sp
                    )
                },
                modifier = Modifier.fillMaxWidth(0.7f),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                )
            )
            OutlinedTextField(
                value = textStateProfessionalSchool.value,
                onValueChange = { textStateProfessionalSchool.value = it},
                label = {
                    Text(
                        text = "Ingrese la escuela profesional",
                        fontSize = 15.sp
                    )
                },
                modifier = Modifier.fillMaxWidth(0.7f),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                )
            )
            OutlinedTextField(
                value = textStateSubjectCode.value,
                onValueChange = { textStateSubjectCode.value = it},
                label = {
                    Text(
                        text = "Ingrese el código de asignatura",
                        fontSize = 15.sp
                    )
                },
                modifier = Modifier.fillMaxWidth(0.7f),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                )
            )
            OutlinedTextField(
                value = textStateNameSubject.value,
                onValueChange = { textStateNameSubject.value = it},
                label = {
                    Text(
                        text = "Ingrese el nombre de la asignatura",
                        fontSize = 15.sp
                    )
                },
                modifier = Modifier.fillMaxWidth(0.7f),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                )
            )
            OutlinedTextField(
                value = textStateSemester.value,
                onValueChange = { textStateSemester.value = it},
                label = {
                    Text(
                        text = "Ingrese el semestre",
                        fontSize = 15.sp
                    )
                },
                modifier = Modifier.fillMaxWidth(0.7f),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                )
            )
            OutlinedTextField(
                value = textStateDuration.value,
                onValueChange = { textStateDuration.value = it},
                label = {
                    Text(
                        text = "Ingrese la duracción",
                        fontSize = 15.sp
                    )
                },
                modifier = Modifier.fillMaxWidth(0.7f),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                )
            )
            Spacer(
                modifier = Modifier.height(20.dp)
            )
            Button(
                onClick = {
                    scope.launch {
                        viewModel.saveUserName(textStatePeriodAcademic.value.text)
                        viewModel.saveProfessionalSchool(textStateProfessionalSchool.value.text)
                    }
                },
                shape = RoundedCornerShape(8.dp),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 6.dp,
                    pressedElevation = 8.dp,
                    disabledElevation = 0.dp
                ),
                modifier = Modifier.padding(5.dp)
            ){
                Text(
                    text = "Enviar",
                    modifier = Modifier.padding(5.dp)
                )
            }

            // Mostrando los valores de datastore

            Spacer(
                modifier = Modifier.height(30.dp)
            )
            Text(
                text = getUserName!!,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 20.sp
            )
            Spacer(
                modifier = Modifier.height(30.dp)
            )
            Text(
                text = getProfessionalSchool!!,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 20.sp
            )
        }
    }


}

/*
@Composable
fun DataStoreInput(
    viewModel: DataStoreViewModel = viewModel(
        factory = DataStoreViewModelFactory(DataStorePreferenceRepository(LocalContext.current))
    ),
    viewModel2: DataStoreViewModel2 = viewModel(
        factory = DataStoreViewModelFactory2(DataStorePreferenceRepository(LocalContext.current))
    )
){
    val context = LocalContext.current
    val dataStorePreferenceRepository: DataStorePreferenceRepository = DataStorePreferenceRepository.getInstance(context)
    val textStatePeriodAcademic = remember {
        mutableStateOf(TextFieldValue())
    }
    val textStateProfessionalSchool = remember {
        mutableStateOf(TextFieldValue())
    }
    val textStateSubjectCode = remember {
        mutableStateOf(TextFieldValue())
    }
    val textStateNameSubject = remember {
        mutableStateOf(TextFieldValue())
    }
    val textStateSemester = remember {
        mutableStateOf(TextFieldValue())
    }
    val textStateDuration = remember {
        mutableStateOf(TextFieldValue())
    }

    val getUserName = viewModel.userName.observeAsState().value
    val getProfessionalSchool = viewModel2.professionalSchool.observeAsState().value
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize()
    ){
        /*
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Purple500)
                .height(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            Text(
                text = "DataStore Preference",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
        */
        Column (
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(
                modifier = Modifier.height(30.dp)
            )
            OutlinedTextField(
                value = textStatePeriodAcademic.value,
                onValueChange = { textStatePeriodAcademic.value = it},
                label = {
                    Text(
                        text = "Ingrese el periodo académico",
                        fontSize = 15.sp
                    )
                },
                modifier = Modifier.fillMaxWidth(0.7f),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                )
            )
            OutlinedTextField(
                value = textStateProfessionalSchool.value,
                onValueChange = { textStateProfessionalSchool.value = it},
                label = {
                    Text(
                        text = "Ingrese la escuela profesional",
                        fontSize = 15.sp
                    )
                },
                modifier = Modifier.fillMaxWidth(0.7f),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                )
            )
            OutlinedTextField(
                value = textStateSubjectCode.value,
                onValueChange = { textStateSubjectCode.value = it},
                label = {
                    Text(
                        text = "Ingrese el código de asignatura",
                        fontSize = 15.sp
                    )
                },
                modifier = Modifier.fillMaxWidth(0.7f),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                )
            )
            OutlinedTextField(
                value = textStateNameSubject.value,
                onValueChange = { textStateNameSubject.value = it},
                label = {
                    Text(
                        text = "Ingrese el nombre de la asignatura",
                        fontSize = 15.sp
                    )
                },
                modifier = Modifier.fillMaxWidth(0.7f),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                )
            )
            OutlinedTextField(
                value = textStateSemester.value,
                onValueChange = { textStateSemester.value = it},
                label = {
                    Text(
                        text = "Ingrese el semestre",
                        fontSize = 15.sp
                    )
                },
                modifier = Modifier.fillMaxWidth(0.7f),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                )
            )
            OutlinedTextField(
                value = textStateDuration.value,
                onValueChange = { textStateDuration.value = it},
                label = {
                    Text(
                        text = "Ingrese la duracción",
                        fontSize = 15.sp
                    )
                },
                modifier = Modifier.fillMaxWidth(0.7f),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                )
            )
            Spacer(
                modifier = Modifier.height(20.dp)
            )
            Button(
                onClick = {
                    scope.launch {
                        viewModel.saveUserName(textStatePeriodAcademic.value.text)
                    }
                    scope.launch {
                        viewModel2.saveProfessionalSchool(textStateProfessionalSchool.value.text)
                    }
                },
                shape = RoundedCornerShape(8.dp),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 6.dp,
                    pressedElevation = 8.dp,
                    disabledElevation = 0.dp
                ),
                modifier = Modifier.padding(5.dp)
            ){
                Text(
                    text = "Enviar",
                    modifier = Modifier.padding(5.dp)
                )
            }

            // Mostrando los valores de datastore

            Spacer(
                modifier = Modifier.height(30.dp)
            )
            Text(
                text = getUserName!!,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 20.sp
            )
            Spacer(
                modifier = Modifier.height(30.dp)
            )
            Text(
                text = getProfessionalSchool!!,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 20.sp
            )
        }
    }


}

*
* */
class DataStoreViewModelFactory(private val dataStorePreferenceRepository: DataStorePreferenceRepository):
        ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                if(modelClass.isAssignableFrom(DataStoreViewModel::class.java)){
                    return DataStoreViewModel(dataStorePreferenceRepository) as T
                }
                throw IllegalStateException()
            }
        }

class DataStoreViewModelFactory2(private val dataStorePreferenceRepository: DataStorePreferenceRepository):
    ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DataStoreViewModel2::class.java)){
            return DataStoreViewModel2(dataStorePreferenceRepository) as T
        }
        throw IllegalStateException()
    }
}


