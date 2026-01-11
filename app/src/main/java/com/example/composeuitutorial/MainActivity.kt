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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeuitutorial.ui.theme.ComposeUiTutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImageCard()
        }
    }
}

@Composable
fun ImageCard() {
    // 컴포즈에서는 특정 데이터의 '상태값'이 변경되면 재구성을 하게 되어 메소드가 재호출됨.
    val isFavorite = rememberSaveable { // remember 키워드가 붙은 값은, 전 호출 직전의 값을 기억한 후 기본값 설정없이 적용됨.
        // val 키워드를 쓰는 이유는, remember가 반환한 객체는 변화하지 않고, 멤버(value)의 값만 변경되기 때문에.
        // 등호(=) 대신 by를 쓰면, .value 형태 대신 객체 이름으로 값을 사용 가능. 단, ~.runtime.getValue, setValue 임포트 필요 (자동 임포트 안 됨.)
        // remember -> rememberSaveable을 사용해야, 화면을 회전해도 전 데이터를 기억한다.
        mutableStateOf(false) // 컴포즈가 데이터 값 변화를 인지하고 재구성을 할 수 있도록 하는 메소드
        // 일반 변수(var a)는 변경이 돼도 재구성이 안 됨.
    }

    Card(
        modifier = Modifier
//            .background(color = Color.Blue)
            .fillMaxWidth(0.5f)
            .padding (16.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        )
    ) {
        Box(
            modifier = Modifier
                .height(200.dp)
        ) {
            // 이미지 파일이 있을 경우 Image 클래스 중 painter를 사용.
            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = "비긴어게인 포스터",
                contentScale = ContentScale.Crop
            )
            Box( // 포스터 위를 덮은 하트가 있는 박스
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopEnd,
            ) {
                IconButton(onClick = {
                    isFavorite.value = !isFavorite.value
                }) {
                    // 이미지 파일이 없을 경우 제공되는 백터 이미지를 쓸 수 있는 imageVector 사용
                    Icon(
                        imageVector = if (isFavorite.value) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "favofite",
                        tint = Color.White
                    )
                }
            }
        }
    }
}