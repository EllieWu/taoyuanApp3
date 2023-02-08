package com.elliewu.taoyuanapp3

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

var redString by mutableStateOf("隱藏\n打卡點");
var blueString by mutableStateOf("隱藏\n報修點")

@Preview(device = Devices.PIXEL_C)
@Preview(device = Devices.PIXEL_3A)
@Preview(showBackground = true)
@Composable
fun infoLayout(navController: NavHostController = rememberNavController(),WorkCode:String = ""){

    Row(
        modifier = Modifier
            .size(width = 1000.dp, height = 80.dp)
            .fillMaxWidth()
            .background(Color.Transparent)
        ,horizontalArrangement = Arrangement.SpaceBetween
        )
    {
        Button(
            contentPadding = PaddingValues(4.dp),
            border = BorderStroke(0.dp, Color.Transparent),
            shape = RoundedCornerShape(0.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(147,146,146)
            ),
            modifier = Modifier.size(width = 130.dp, height = 50.dp),
            onClick = {navController.navigate(Screen.MA3_1_1_info.withArgs(WorkCode))}
        ){
            Row(
                modifier = Modifier
                    .size(width = 180.dp, height = 200.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,)
            {
                Image(
                    painterResource(id = R.drawable.list),
                    contentDescription = "",
                    modifier = Modifier.size(25.dp),
                    colorFilter = ColorFilter.tint(Color(255,255,255))
                )
                Text(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 7.dp),
                    text = "巡檢資訊",
                    textAlign = TextAlign.Center,
                    color = Color(255,255,255),
                    fontSize = 20.sp,
                    )
            }
        }
        Row(modifier = Modifier
            .fillMaxHeight()
            .padding(end = 55.dp)){
            Button(
                contentPadding = PaddingValues(0.dp),
                shape = RoundedCornerShape(0.dp),
                modifier = Modifier
                    .size(60.dp, 60.dp)
                    .padding(end = 5.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(166,69,76)
                ),
                 onClick = { redDotIsVis = !redDotIsVis;
                     if (redDotIsVis) redString = "隱藏\n打卡點"
                     else redString = "顯示\n打卡點"}) {
                Text(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 10.dp),
                    text = redString,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    color = Color(255,255,255)
                )
            }
            Button(
                contentPadding = PaddingValues(0.dp),
                shape = RoundedCornerShape(0.dp),
                modifier = Modifier.size(60.dp,60.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(79,143,193)
                ),
                onClick = { blueDotIsVis = !blueDotIsVis;
                if (blueDotIsVis) blueString = "隱藏\n報修點"
                else blueString = "顯示\n報修點"}) {
                Text(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 10.dp),
                    text = blueString,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    color = Color(255,255,255)
                )
            }
        }
    }
}