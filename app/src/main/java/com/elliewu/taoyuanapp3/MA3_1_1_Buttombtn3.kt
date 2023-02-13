package com.elliewu.taoyuanapp3

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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

@Preview(device = Devices.PIXEL_C)
@Preview(device = Devices.PIXEL_3A)
@Composable
fun MA3_1_1_Bottombtn3(WorkCode:String?="",WorkTime:String?="",navController: NavHostController = rememberNavController()){
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
                .background(Color(65, 96, 176)),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                modifier = Modifier
                    .padding(start = 5.dp)
                    .size(30.dp),
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "BackIcon",
                tint = Color.White
            )
            ClickableText(
                text = AnnotatedString("返回"),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 20.sp,
                    textAlign = TextAlign.End,
                ),
                onClick = {
                    //navController.navigate(Screen.MA3_1_1.route)
                    val MA3_1_1_fullRoutePath = Screen.MA3_1_1.route + "?WorkCode=${WorkCode}&WorkTime=${WorkTime}"
                    navController.navigate(MA3_1_1_fullRoutePath)
                }
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(end = 40.dp),
                //size(width = 250.dp, height = 30.dp),

                text = "新增報修",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = Color(255, 255, 255),
            )
        }
        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .bottomBorder(2.dp, Color(197, 202, 208))
//                .background(Color(236, 243, 253)),
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp, bottom = 0.dp, start = 10.dp, end = 10.dp)
                .verticalScroll(rememberScrollState())
        ) {
            //ReportInfo(MA3_3_NEW1_msggg)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp, start = 20.dp, end = 20.dp)
            ) {
                Text(
                    text = "報修主旨",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color(85,86,90)
                )
                var titleValue = ""
                BasicTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = titleValue,
                    onValueChange = {
                        titleValue = it
                        //MA3_3_NEW1_msggg.ReportTitle = it
                    },
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(50, 53, 60),
                    ),
                    decorationBox = { innerTextField ->
                        Row(
                            modifier = Modifier
                                .padding(start = 36.dp) // margin left and right
                                .fillMaxWidth()
                                .background(color = Color.White, shape = RoundedCornerShape(size = 8.dp))
                                .border(
                                    width = 2.dp,
                                    color = Color.White,
                                    shape = RoundedCornerShape(size = 8.dp)
                                )
                                .padding(
                                    start = 8.dp,
                                    end = 16.dp,
                                    top = 8.dp,
                                    bottom = 8.dp
                                ), // inner padding
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Spacer(modifier = Modifier.width(width = 8.dp))
                            innerTextField()
                        }
                    }
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp, start = 20.dp, end = 20.dp)
            ) {
                Text(
                    text = "報修地點",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color(85,86,90)
                )
                Row(verticalAlignment = Alignment.CenterVertically){
                    Icon(
                        //modifier = Modifier.padding(start = 82.dp),
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Favorite icon",
                        tint = Color(64,111,158)
                    )
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(84,117,162)
                        ),
                        elevation = null,
                        //modifier = Modifier.padding(start = 82.dp),
                        contentPadding = PaddingValues(0.dp),
                        onClick = {}
                    ){
                        Text(
                            modifier = Modifier.padding(horizontal = 8.dp),
                            text = "重新定位",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color(255,255,255),
                            textDecoration = TextDecoration.Underline,
                        )
                    }
                }
            }
            Row(modifier = Modifier.fillMaxSize()
                .clip(shape = RoundedCornerShape(7.dp))
                .background(Color.White, shape = RoundedCornerShape(7.dp))
                .padding(vertical = 10.dp, horizontal = 10.dp)){
                Column() {
                    Row(verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 5.dp)
                    )
                    {
                        Text(
                            text = "GIS X座標:",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(128,127,129)
                        )
                        Text(
                            modifier = Modifier.padding(start = 20.dp),
                            text = "",
                            color = Color(163,76,60),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 5.dp))
                    {
                        Text(
                            text = "GIS Y座標:",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(128,127,129)
                        )
                        Text(
                            modifier = Modifier.padding(start = 20.dp),
                            text = "",
                            color = Color(163,76,60),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}