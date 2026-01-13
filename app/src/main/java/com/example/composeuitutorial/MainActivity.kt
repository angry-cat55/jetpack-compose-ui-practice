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
import androidx.compose.runtime.getValue
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
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composeuitutorial.ui.theme.ComposeUiTutorialTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    // remember 키워드를 사용한 변수는 setContent 안의 함수가 다시 그려질 때까지만 데이터 기억.
    // 화면이 회전하여 액티비티 자체가 날아가면 remember도 삭제.
    // ViewModel은 액티비티가 파괴돼도 객체 자체가 계속 살아있을 수 있음.
    private val viewModel by viewModels<MainViewModel>()
    // val viewModel = MainViewModel()을 쓰면, 화면이 회전될 때마다 새로운 뷰모델 객체가 생성되어 데이터 보존 X
    // by : 객체 생성을 viewModels에게 위임
    // viewModels : 안드로이드 시스템 내부의 '뷰모델 창고'를 관리.
    /* 작동원리
    * MainActivity 켜짐 -> viewModels()가 뷰모델을 최초 생성 ->
    * 화면 회전으로 인한 액티비티 파괴 후 재생성 -> viewModel() 다시 호출 ->
    * 이미 만든 뷰모델 존재 -> 데이터를 유지하며 뷰모델 전달 ->
    * 앱 종료 시 액티비티 완전 종료 -> ViewModel도 같이 해제 */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val testValue = remember {
                mutableStateOf("Hello")
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = viewModel.data.value,
                    // text = testValue.value, // remember 키워드 변수 사용 시
                    fontSize = 30.sp
                )
                Button(onClick = {
                    viewModel.data.value = "World"
                    // testValue.value = "World" // 화면을 회전하면 액티비티가 파괴되어 다시 Hello로 초기화됨.
                }) {
                    Text(text = "Change")
                }
            }
        }
    }
}

class MainViewModel: ViewModel() {
    val data = mutableStateOf("Hello")
    // remember 키워드가 없어도 변수 기억 가능
    // 화면 회전 등으로 인해 activity가 파괴되고 재생성되어도 내부 데이터는 안 죽고 계속 살아있음.
}