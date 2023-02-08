package com.elliewu.taoyuanapp3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

data class RepairInfoList(
    val RepairCode: String,
    val State: String,
    val Manager: String,
    val Longitude:String,
    val Latitude:String,
    val RepairTitle:String,
    val RepairContent:String,
    val RepairPhoto:String,

)
var RepairListData = RepairInfoList(
    "111032504",
    "測試中",
    "陳慶明",
    "121.798385",
    "25.140054",
    "階梯有幾處破損",
    "教育中心外側階梯破損嚴重",
    ""
)
var MA3_2_1_msggg by mutableStateOf(RepairListData)



@Preview(device = Devices.PIXEL_C)
@Preview(device = Devices.PIXEL_3A)
@Preview(showBackground = true)
@Composable
fun MA3_2_1(RepairCode: String? = "", navController : NavHostController = rememberNavController()){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(232, 232, 232)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(

            modifier = Modifier
                .fillMaxWidth()
                .size(width = 250.dp, 50.dp)
                .background(Color(62, 83, 140)),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ClickableText(
                text = AnnotatedString("返回"),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 20.sp,
                    textAlign = TextAlign.End,
                    textDecoration = TextDecoration.Underline,
                ),
                onClick = {
                    navController.navigate(Screen.MA3_2.route)
                },
                modifier = Modifier.padding(start = 20.dp)
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(end = 40.dp),
                //size(width = 250.dp, height = 30.dp),

                text = "維修工單內容",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = Color(255, 255, 255),
            )
        }
        Column(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .background(Color(255, 255, 255))) {
                RepairInfoTable(RepairListData)
            }
        }
//        Text(text = "yoyo: ${RepairCode.toString()}")
    }
}

@Composable
fun RepairInfoTable(list: RepairInfoList){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        //horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .bottomBorder(1.dp, Color(232, 232, 232)),
    )
    {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp), horizontalArrangement = Arrangement.SpaceBetween)
        {
            Text(
                text = "報修單號",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,

                color = Color(128,127,129),
                modifier = Modifier.padding( vertical = 10.dp),
                //.fillMaxWidth(),
                textAlign = TextAlign.Start,
            )
            Text(
                text = list.RepairCode,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(vertical = 10.dp),
                //.fillMaxWidth(),
                textAlign = TextAlign.Center,
            )
        }
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        //horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .bottomBorder(1.dp, Color(232, 232, 232)),
    )
    {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp), horizontalArrangement = Arrangement.SpaceBetween)
        {
            Text(
                text = "完成狀態",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,

                color = Color(128,127,129),
                modifier = Modifier.padding( vertical = 10.dp),
                //.fillMaxWidth(),
                textAlign = TextAlign.Start,
            )
            Text(
                text = list.State,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(vertical = 10.dp),
                textAlign = TextAlign.Center,
            )
        }
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        //horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .bottomBorder(1.dp, Color(232, 232, 232)),
    )
    {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp), horizontalArrangement = Arrangement.SpaceBetween)
        {
            Text(
                text = "指派人",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,

                color = Color(128,127,129),
                modifier = Modifier.padding( vertical = 10.dp),
                textAlign = TextAlign.Start,
            )
            Text(
                text = list.Manager,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(vertical = 10.dp),
                textAlign = TextAlign.Center,
            )
        }
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        //horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .bottomBorder(1.dp, Color(232, 232, 232)),
    )
    {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp), horizontalArrangement = Arrangement.SpaceBetween)
        {
            Text(
                text = "GIS X",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(128,127,129),
                modifier = Modifier.padding( vertical = 10.dp),
                textAlign = TextAlign.Start,
            )
            Text(
                text = list.Longitude,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(vertical = 10.dp),
                textAlign = TextAlign.Center,
            )
        }
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        //horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .bottomBorder(1.dp, Color(232, 232, 232)),
    )
    {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp), horizontalArrangement = Arrangement.SpaceBetween)
        {
            Text(
                text = "GIS Y",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,

                color = Color(128,127,129),
                modifier = Modifier.padding( vertical = 10.dp),
                textAlign = TextAlign.Start,
            )
            Text(
                text = list.Latitude,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(vertical = 10.dp),
                textAlign = TextAlign.Center,
            )
        }
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        //horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .bottomBorder(1.dp, Color(232, 232, 232)),
    )
    {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp), horizontalArrangement = Arrangement.SpaceBetween)
        {
            Text(
                text = "報修主旨",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(128,127,129),
                modifier = Modifier.padding( vertical = 10.dp),
                textAlign = TextAlign.Start,
            )
            Text(
                text = list.RepairTitle,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                modifier = Modifier.padding(vertical = 10.dp),
                textAlign = TextAlign.Center,
            )
        }
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        //horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .bottomBorder(1.dp, Color(232, 232, 232)),
    )
    {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp), horizontalArrangement = Arrangement.SpaceBetween)
        {
            Text(
                text = "報修說明",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,

                color = Color(128,127,129),
                modifier = Modifier.padding( vertical = 10.dp),
                textAlign = TextAlign.Start,
            )
            Text(
                text = list.RepairContent,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(vertical = 10.dp),
                textAlign = TextAlign.Center,
            )
        }
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        //horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .bottomBorder(1.dp, Color(232, 232, 232)),
    )
    {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp), horizontalArrangement = Arrangement.SpaceBetween)
        {
            Text(
                text = "照片",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(128,127,129),
                modifier = Modifier.padding( vertical = 10.dp),
                textAlign = TextAlign.Start,
            )
            Text(
                text = list.RepairPhoto,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(vertical = 10.dp),
                textAlign = TextAlign.Center,
            )
        }
    }
}

