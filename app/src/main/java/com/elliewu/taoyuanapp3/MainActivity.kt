package com.elliewu.taoyuanapp3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elliewu.taoyuanapp3.ui.theme.TaoyuanApp3Theme
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import android.graphics.fonts.FontStyle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.ui.draw.paint
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.content.Context
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Done
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.concurrent.timer


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaoyuanApp3Theme {
                //DefaultPreview();
                Navigation();
            }
        }
    }
}


//@Composable
//fun login(){
//    Box(modifier = Modifier
//        .fillMaxWidth()
//        .fillMaxHeight()
//        .paint(painterResource(id = R.drawable.login_bg))){
//        Column(modifier = Modifier
//            .fillMaxWidth()
//            .padding(start = 50.dp, end = 50.dp),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center,) {
//            Image(
//                painterResource(id = R.drawable.logo), contentDescription = "",
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(top = 140.dp, bottom = 20.dp))
//            Text("智慧巡檢系統", fontSize = 24.sp, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold,
//                color = Color(180,203,237),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(bottom = 30.dp))
//            Text("帳號",fontSize = 24.sp,textAlign = TextAlign.Start,fontWeight = FontWeight.Bold,
//                color = Color(255,255,255),
//                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp))
//
//            var account by remember { mutableStateOf("") }
//            val maxLength = 110
//            TextField( modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
//                value = account,
//                colors = TextFieldDefaults.textFieldColors(
//                    cursorColor = Color.Black,
//                    backgroundColor = Color(255,255,255),
//                    focusedIndicatorColor = Color.Transparent,
//                    unfocusedIndicatorColor = Color.Transparent), onValueChange = {if (it.length <= maxLength) account = it})
//
//            Text("密碼",fontSize = 24.sp,textAlign = TextAlign.Start,fontWeight = FontWeight.Bold,
//                color = Color(255,255,255),
//                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp))
//
//            var password by remember { mutableStateOf("") }
//            TextField( modifier = Modifier.fillMaxWidth().padding(bottom = 40.dp),
//                value = password,
//                colors = TextFieldDefaults.textFieldColors(
//                    cursorColor = Color.Black,
//                    backgroundColor = Color(255,255,255),
//                    focusedIndicatorColor = Color.Transparent,
//                    unfocusedIndicatorColor = Color.Transparent), onValueChange = {if (it.length <= maxLength) password = it})
//
//            Button(colors = ButtonDefaults.buttonColors(backgroundColor = Color(red = 255, green = 166, blue = 0)),modifier = Modifier.fillMaxWidth().size(width = 50.dp, height = 60.dp), onClick = {
//                //按 onclick 的事件
//            },) {
//                //按鈕的文字
//                Text(text = "登入", fontSize = 24.sp, fontWeight = FontWeight.Bold,
//                    color = Color(255,255,255)
//                )
//
//            }
//        }
//    }
//}

@Preview(
    name = "Device",
    showSystemUi = true,
    showBackground = true
)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    //login();
}