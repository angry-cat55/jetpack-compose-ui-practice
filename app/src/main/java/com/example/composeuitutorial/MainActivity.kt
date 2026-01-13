package com.example.composeuitutorial

import androidx.compose.ui.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composeuitutorial.ui.theme.ComposeUiTutorialTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen()
        }
    }
}
/**********************************************
* 각 객체의 타입(객체: 타입)이 어떤지 확인하는 커밋 *
***********************************************/

@Composable
fun HomeScreen(viewModel: MainViewModel = viewModel()) {
    var text1: MutableState<String> = remember {
        mutableStateOf("Hello World")
    }
    val (text2: String, setText: (String) -> Unit) = remember {
        mutableStateOf("Hello World")
    }
    var text3:String by remember {
        mutableStateOf("Hello World")
    }

    // 라이브 데이터라는 기법 사용할 때 활용 가능한 코드
    val text4: State<String?> = viewModel.liveData.observeAsState("Hello World")

    Column() {
        Text("Hello World")
        Button(onClick = {
            text1.value = "변경"
            print(text1.value)
//            text2.value = "변경" // 에러
//            print(text2.value) // 에러
            setText("변경")
            print(text2)
            text3 = "변경"
            print(text3)

//            viewModel.value.value = "변경" // 에러
            viewModel.changeValue("변경")
            print(viewModel.value.value)
        }) {
            Text("클릭")
        }
        TextField(value = text2, onValueChange = setText)
    }
}

class MainViewModel: ViewModel() {
    private val _value: MutableState<String> = mutableStateOf("Hello World")
    val value: State<String> = _value // 업캐스팅(MutableState -> State)

    private val _liveData = MutableLiveData<String>()
    val liveData: LiveData<String> = _liveData

    fun changeValue(newValue: String) {
        _value.value = newValue;
    }
}