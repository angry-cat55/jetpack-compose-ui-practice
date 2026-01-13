package com.example.composeuitutorial

import androidx.compose.ui.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeuitutorial.ui.theme.ComposeUiTutorialTheme
import kotlinx.coroutines.launch


/*Todo*/
// 1. 다 하고 커밋하고 강의 코드대로 친 후 버전 차이 물어보기
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val (text, setValue) = remember { // mutableStateOf를 구조 분해
                // text => value값, setValue => 전달받은 String 값으로 value값 변경
                mutableStateOf("")
            }
            // SnackBar 혹은 FloatingAction 버튼 등은 Scaffold를 사용해야 함.
            val snackbarHostState = remember {
                SnackbarHostState()
            }
            val scope = rememberCoroutineScope()
            val keyboardController = LocalSoftwareKeyboardController.current
            Scaffold(
                snackbarHost = {
                    SnackbarHost(snackbarHostState)
                }
            ) {paddingValues ->
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextField(
                        value = text,
                        onValueChange = setValue // TextField의 값이 변경되면 setValue라는 함수 호출
                    )
                    Button(onClick = {
                        keyboardController?.hide() // null이 아닐 때만 호출하는 '안전 호출' 키워드
                        scope.launch {
                            snackbarHostState.showSnackbar("Hello $text")
                        }
                    }) {
                        Text("Click!")
                    }
                }
            }
        }
    }
}