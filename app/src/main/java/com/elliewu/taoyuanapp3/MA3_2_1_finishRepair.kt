package com.elliewu.taoyuanapp3

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject


@Preview(device = Devices.PIXEL_C)
@Preview(device = Devices.PIXEL_3A)
@Composable
fun MA3_2_1_finishRepair(navController : NavHostController = rememberNavController()){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(231, 232, 231)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(

            modifier = Modifier
                .fillMaxWidth()
                .size(width = 250.dp, 50.dp)
                .background(Color(62, 83, 140)),
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
                    //navController.navigate(Screen.MA3_2_1.route)
                    var fullMA3_2_1_path = Screen.MA3_2_1.route + "?RepairCode=${MA3_2_1_msggg.RepairCode}&State=${MA3_2_1_msggg.State}"
                    navController.navigate(fullMA3_2_1_path)
                },
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(end = 40.dp),
                //size(width = 250.dp, height = 30.dp),

                text = "維護完工填報",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = Color(255, 255, 255),
            )
        }
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp, bottom = 0.dp, start = 20.dp, end = 20.dp)
            .verticalScroll(rememberScrollState())) {
//            Column(modifier = Modifier
//                .fillMaxWidth()
//                .background(Color(255, 255, 255))) {
//
//            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            {
                Text(
                    text = "報修單號",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,

                    color = Color(128,127,129),
                    //modifier = Modifier.padding( bottom = 10.dp),
                    //.fillMaxWidth(),
                    textAlign = TextAlign.Start,
                )
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 65.dp), horizontalArrangement = Arrangement.Start)
                {
                    Text(
                        text = MA3_2_1_msggg.RepairCode,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,

                        color = Color(103,103,103),
                        //modifier = Modifier.padding(bottom = 10.dp),
                        //.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            {
                Text(
                    text = "報修主旨",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,

                    color = Color(128,127,129),
                   // modifier = Modifier.padding( bottom = 10.dp),
                    //.fillMaxWidth(),
                    textAlign = TextAlign.Start,
                )
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 65.dp), horizontalArrangement = Arrangement.Start)
                {
                    Text(
                        text = MA3_2_1_msggg.RepairTitle,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,

                        color = Color(103,103,103),
                        //modifier = Modifier.padding(bottom = 10.dp),
                        //.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            {
                Text(
                    text = "完成狀態",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,

                    color = Color(128,127,129),
                    // modifier = Modifier.padding( bottom = 10.dp),
                    //.fillMaxWidth(),
                    textAlign = TextAlign.Start,
                )
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp), horizontalArrangement = Arrangement.Start)
                {
                    var value = "123"
                    BasicTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = MA3_2_1_msggg.State,
                        onValueChange = { newText ->
                            value = newText
                        },
                        textStyle = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(103,103,103),
                            textAlign = TextAlign.Center,
                        ),
                        decorationBox = { innerTextField ->
                            Row(
                                modifier = Modifier
                                    .padding(start = 36.dp) // margin left and right
                                    .fillMaxWidth()
                                    .background(
                                        color = Color.White,
                                        shape = RoundedCornerShape(size = 8.dp)
                                    )
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
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {

                                Spacer(modifier = Modifier.width(width = 8.dp))
                                innerTextField()
                            }
                        }
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            {
                var repairRemark by remember { mutableStateOf("維修備註") }
                TextField(
                    modifier = Modifier
                        .size(2000.dp, 180.dp)
                        .fillMaxSize(),
                    value = repairRemark,
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(103,103,103),
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White,
                        cursorColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    onValueChange = {
                        repairRemark = it
                    },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.None,
                        autoCorrect = true, keyboardType = KeyboardType.Text, imeAction = ImeAction.Done
                    ),
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            {
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(86,107,183)),
                    shape = RoundedCornerShape(50),
                    elevation = null,
                    onClick = {},
                )
                {
                    Row(
                        modifier= Modifier.padding(horizontal = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ){
                        Icon(
                            modifier = Modifier
                                .size(20.dp),
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "BackIcon",
                            tint = Color.White
                        )
                        Text(
                            text = "拍照",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                        )
                    }
                }
            }

        }
    }
   // Column() {
        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp, bottom = 10.dp, start = 80.dp, end = 80.dp))
        {
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(86,107,183)),
                elevation = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                onClick = {}
            ){
                Text(
                    text = "送出",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White
                )
            }
        }
    //}

}
