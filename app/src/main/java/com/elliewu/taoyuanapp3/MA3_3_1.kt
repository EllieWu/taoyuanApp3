package com.elliewu.taoyuanapp3

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import java.text.SimpleDateFormat
import java.util.*




var MA3_3_1_msggg by mutableStateOf(ReportListData)

@Preview(device = Devices.PIXEL_C)
@Preview(device = Devices.PIXEL_3A)
@Preview(showBackground = true)
@Composable
fun MA3_3_1(navController: NavHostController = rememberNavController()) {
    var ReportCode: String? = ""
    ReportCode = MA3_3_1_ReportCode;
    MA3_3_1_MakeListCom(ReportCode.toString());
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
                    navController.navigate(Screen.MA3_3.route)
                }
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(end = 40.dp),
                //size(width = 250.dp, height = 30.dp),

                text = "編輯報修內容",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = Color(255, 255, 255),
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .bottomBorder(2.dp, Color(197, 202, 208))
                .background(Color(236, 243, 253)),
        ) {
            ReportInfo(MA3_3_1_msggg)
        }
    }

}



@Composable
private fun MyUI(list: ReportInfoList) {
    var titleValue by remember {
        mutableStateOf("")
    }

    var contentValue by remember {
        mutableStateOf("")
    }
    titleValue = list.ReportTitle
    contentValue = list.ReportContent
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp, start = 20.dp, end = 20.dp)
    ) {
        Text(
            text = "報修主旨:",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color(85,86,90)
        )
        BasicTextField(
            modifier = Modifier.fillMaxWidth(),
            value = titleValue,
            onValueChange = { newText ->
                titleValue = newText
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
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp, start = 20.dp, end = 20.dp)
    )
    {
        Text(
            text = "報修內容",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color(85,86,90)
        )
    }
    TextField(
        modifier = Modifier

            .size(2000.dp, 180.dp)
            .fillMaxSize()
            .padding(start = 20.dp, 0.dp, 20.dp, 0.dp),
        value = contentValue,
        textStyle = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color(50, 53, 60),
        ),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            cursorColor = Color.Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        onValueChange = {

        },
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            autoCorrect = true, keyboardType = KeyboardType.Text, imeAction = ImeAction.Done
        ),
    )
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp, start = 20.dp, end = 20.dp))
    {
        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(170,170,170)),
            shape = RoundedCornerShape(50),
            elevation = null,
            onClick = {},
        )
        {
            Row(verticalAlignment = Alignment.CenterVertically,
            ){
                Icon(
                    modifier = Modifier
                        .size(25.dp),
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "BackIcon",
                    tint = Color.White
                )
                Text(
                    text = "更換照片",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textDecoration = TextDecoration.Underline
                )
            }
        }
    }
    //照片位置






    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp, start = 80.dp, end = 80.dp)){
        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(84,117,162)),
            elevation = null,
            modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp),
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

}
//TODO:Jeremy增加
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MA3_3_1_MakeListCom(ReportCode:String){
    MA3_3_1_MakeList(ReportCode)
}
fun MA3_3_1_MakeList( ReportCode:String){
    GlobalScope.launch(Dispatchers.IO) {
        var RequestJsonObject = JSONObject();
        RequestJsonObject.put("Function", "ReportContent")
        RequestJsonObject.put("ReportCode", ReportCode)
        RequestJsonObject.put("ReportType", "外巡報修")
        val responseString = HttpRequestTest(RequestJsonObject)
        Log.d("MA3_3_1",responseString)
        if(responseString!="Error"){
            var gson = Gson();
            var WorkInfoResponse:ReportContent_Response = gson.fromJson(responseString,ReportContent_Response::class.java)
            var workListDatas = MA3_3_1_msggg
            if(WorkInfoResponse.OutsideRepair != null){
                var outsiderepair = WorkInfoResponse.OutsideRepair
                if (outsiderepair != null) {
                    workListDatas = ReportInfoList(
                        outsiderepair.ReportCode.toString(),
                        outsiderepair.Longitude.toString(),
                        outsiderepair.Latitude.toString(),
                        outsiderepair.ReportTitle.toString(),
                        outsiderepair.ReportContent.toString(),
                        outsiderepair.ReportPhoto.toString(),
                        outsiderepair.Edit.toString()
                    )
                    Log.d("ReportTitle",outsiderepair.ReportTitle.toString())
                    Log.d("ReportContent",outsiderepair.ReportContent.toString())
                    Log.d("ReportPhoto",outsiderepair.ReportPhoto.toString())
                }
            }
            MA3_3_1_msggg = workListDatas
        }
    }
}