package com.elliewu.taoyuanapp3

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun login(navController: NavController){
    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .paint(painterResource(id = R.drawable.login_bg))){
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 50.dp, end = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,) {
            Image(
                painterResource(id = R.drawable.logo), contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 140.dp, bottom = 20.dp))
            Text("智慧巡檢系統", fontSize = 24.sp, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold,
                color = Color(180,203,237),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp))
            Text("帳號",fontSize = 24.sp,textAlign = TextAlign.Start,fontWeight = FontWeight.Bold,
                color = Color(255,255,255),
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp))

            var account by remember { mutableStateOf("") }
            val maxLength = 110
            TextField( modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
                value = account,
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = Color.Black,
                    backgroundColor = Color(255,255,255),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent), onValueChange = {if (it.length <= maxLength) account = it})

            Text("密碼",fontSize = 24.sp,textAlign = TextAlign.Start,fontWeight = FontWeight.Bold,
                color = Color(255,255,255),
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp))

            var password by remember { mutableStateOf("") }
            TextField( modifier = Modifier.fillMaxWidth().padding(bottom = 40.dp),
                value = password,
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = Color.Black,
                    backgroundColor = Color(255,255,255),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent), onValueChange = {if (it.length <= maxLength) password = it})

            Button(colors = ButtonDefaults.buttonColors(backgroundColor = Color(red = 255, green = 166, blue = 0)),modifier = Modifier.fillMaxWidth().size(width = 50.dp, height = 60.dp), onClick = {
                navController.navigate(Screen.MA3_1.route)
            },) {
                //按鈕的文字
                Text(text = "登入", fontSize = 24.sp, fontWeight = FontWeight.Bold,
                    color = Color(255,255,255)
                )
            }
        }
    }
}