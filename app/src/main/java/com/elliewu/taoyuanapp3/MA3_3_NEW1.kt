package com.elliewu.taoyuanapp3

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
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
import java.util.*

data class ReportInfoList(
    val ReportCode: String,
    val Longitude: String,
    val Latitude: String,
    var ReportTitle: String,
    var ReportContent: String,
    val ReportPhoto: String,
    val Edit: String,

    )

var ReportListData = ReportInfoList(
    "777777777",
    "45.259898412",
    "33.444444",
    "",
    "",
    "/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAMCAgMCAgMDAwMEAwMEBQgFBQQEBQoHBwYIDAoMDAsKCwsNDhIQDQ4RDgsLEBYQERMUFRUVDA8XGBYUGBIUFRT/2wBDAQMEBAUEBQkFBQkUDQsNFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBT/wAARCAAZABoDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDyz9in9j9f2idS1PxD4mu59I8AaEc3l1FhXunA3NEjEHaAoyzYOARjk5H3l4W8L/ss3GiWVvoPg3QdW0m4XbHfHTjIrjOCWklG49OvP1qh/wAE92sp/wBiXy7IK1wG1JblU5bzctjOO+0p+GK+S9U8YanpPi7TtOhe7SwwFupEYqkTSZEQPuxXt0465r3spyP+1KMqkaqg4uK1V97+a2/I+YznH4nB8kcNa7u3dX0XTdbne/tv/wDBP7RPCHhC9+I/wvhkt9Ns18/U9DDmREizzNATyAucshJwMkYxivzy4/u/pX7Nfs+eIJ/E37Gvie78SXLXdukOt20rTnOIY3mQD8FAFfjMygsSDxnivnsZRWHrSpp3s2vuPoKVT2tONS1rq59K/sW/tiXn7L/iW7tNStptW8F6s6G+soSPNgkHAniDEDdg4K5G4AcjAr9AP+Gmv2W/F2k6zc3XiTSY4dbkjm1C3vLeeOWR0VFQ425UqI0xt7jPXJr8aV/oab/e/wA96541ZQ2NWk9z7w/a2/be8Kaj4Dv/AIX/AAZtXtPDupTTTavqzRtGJzLKZZUhDHdh3LbmYDIJAGDmvhHIpn8P/AadUSk5O7Ef/9k=",
    "TRUE",
)

var MA3_3_NEW1_msggg by mutableStateOf(ReportListData)

@Preview(device = Devices.PIXEL_C)
@Preview(device = Devices.PIXEL_3A)
@Preview(showBackground = true)
@Composable
fun MA3_3_NEW1(navController: NavHostController = rememberNavController()) {
    var ReportCode: String? = ""
    ReportCode = MA3_3_1_ReportCode;
    MA3_3_NEW1_MakeListCom(ReportCode.toString());
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
            ReportInfo(MA3_3_NEW1_msggg)
        }
    }
}

@Composable
fun ReportInfo(list: ReportInfoList) {
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            //horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 10.dp, start = 20.dp, end = 20.dp)
        ) {
            Text(
                text = "報修單號:",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color(128, 127, 129)
            )
            Text(
                modifier = Modifier.padding(start = 45.dp),
                text = list.ReportCode,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color(83, 84, 88),
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .bottomBorder(2.dp, Color(197, 202, 208))
                .background(Color(229, 236, 246)),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                //horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 10.dp, start = 20.dp, end = 20.dp)
            ) {
                Text(
                    text = "GIS X座標:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color(128, 127, 129)
                )
                Text(
                    modifier = Modifier.padding(start = 37.dp),
                    text = list.Longitude,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color(50, 53, 60),
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                //horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp, start = 20.dp, end = 20.dp)
            ) {
                Text(
                    text = "GIS Y座標:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color(128, 127, 129)
                )
                Text(
                    modifier = Modifier.padding(start = 38.dp),
                    text = list.Latitude,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color(50, 53, 60),
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                //horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp, bottom = 10.dp, start = 20.dp, end = 20.dp)
            ) {
                Text(
                    text = "地圖:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color(128, 127, 129)
                )
                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Transparent
                    ),
                    elevation = null,
                    modifier = Modifier.padding(start = 82.dp),
                    contentPadding = PaddingValues(0.dp),
                    onClick = {}
                ){
                    Icon(
                        //modifier = Modifier.padding(start = 82.dp),
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Favorite icon",
                        tint = Color(64,111,158)
                    )
                    Text(
                        text = "GIS定位",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color(64,111,158),
                        textDecoration = TextDecoration.Underline,
                    )
                }
            }
        }
        MA3_3_NEW1_UI(list);
    }
}


@Composable
fun MA3_3_NEW1_UI(list: ReportInfoList) {
    Log.d("ReportInfoList","$list")
    var titleValue by remember {
        mutableStateOf(list.ReportTitle)
    }

    var contentValue by remember {
        mutableStateOf(list.ReportContent)
    }
    titleValue = list.ReportTitle
    contentValue = list.ReportContent
    Log.d("titleValue",titleValue);
    Log.d("contentValue",contentValue);
//    var titleValue = list.ReportTitle
//    var contentValue = list.ReportContent
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
            onValueChange = {
                titleValue = it
                MA3_3_NEW1_msggg.ReportTitle = it
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
            contentValue = it
            MA3_3_NEW1_msggg.ReportContent = it
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
            .padding(top = 10.dp, bottom = 10.dp, start = 20.dp, end = 20.dp))
    {
        val imageBytes = Base64.decode(list.ReportPhoto, 0)
        val image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        if(image != null)
        {
            Image(
                modifier = Modifier.size(350.dp),
                contentScale = ContentScale.FillWidth,
                bitmap = image.asImageBitmap(),
                contentDescription = "contentDescription")
        }
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp, start = 80.dp, end = 80.dp))
    {
        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(84,117,162)),
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

}

//TODO:Jeremy增加
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MA3_3_NEW1_MakeListCom(ReportCode:String){
    MA3_3_NEW1_MakeList(ReportCode)
}
fun MA3_3_NEW1_MakeList( ReportCode:String){
    GlobalScope.launch(Dispatchers.IO) {
        var RequestJsonObject = JSONObject();
        RequestJsonObject.put("Function", "ReportContent")
        RequestJsonObject.put("ReportCode", ReportCode)
        RequestJsonObject.put("ReportType", "外巡報修")
        val responseString = HttpRequestTest(RequestJsonObject)
        Log.d("MA3_3_NEW1",responseString)
        if(responseString!="Error"){
            var gson = Gson();
            var WorkInfoResponse:ReportContent_Response = gson.fromJson(responseString,ReportContent_Response::class.java)
            var workListDatas = MA3_3_NEW1_msggg
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
            MA3_3_NEW1_msggg = workListDatas
        }
    }
}