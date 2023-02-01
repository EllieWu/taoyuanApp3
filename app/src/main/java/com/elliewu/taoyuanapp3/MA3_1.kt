package com.elliewu.taoyuanapp3

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun MA3_1(navController: NavController) {
    var text by remember {
        mutableStateOf("")
    }
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp)
    ) {
        TextField(value = text,
            onValueChange = {
                text = it
            }, modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(colors = ButtonDefaults.buttonColors(backgroundColor = Color(red = 255, green = 166, blue = 0)),modifier = Modifier.fillMaxWidth().size(width = 50.dp, height = 60.dp), onClick = {
            navController.navigate(Screen.login.route)
        },) {
            //按鈕的文字
            Text(text = "登入", fontSize = 24.sp, fontWeight = FontWeight.Bold,
                color = Color(255,255,255)
            )
        }
    }
}