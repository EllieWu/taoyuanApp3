package com.elliewu.taoyuanapp3

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
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
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject


@Preview(device = Devices.PIXEL_C)
@Preview(device = Devices.PIXEL_3A)
@Composable
fun MA3_1_1_Buttonbtn1(WorkTime:String?="",
                       WorkCode:String?="",
                       Longitude:String?="",
                       Latitude:String?="",
                       navController: NavHostController = rememberNavController()){
    var reportContentValue by remember { mutableStateOf("") }
    Log.d("WorkTime",WorkTime.toString())
    Log.d("WorkCode",WorkTime.toString())
    Log.d("Longitude",WorkTime.toString())
    Log.d("Latitude",WorkTime.toString())
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
                    navController.navigate(Screen.MA3_1_1.route)
                },
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(end = 40.dp),
                //size(width = 250.dp, height = 30.dp),

                text = "巡檢填報",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = Color(255, 255, 255),
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp, bottom = 0.dp, start = 20.dp, end = 20.dp)
                .verticalScroll(rememberScrollState())) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            {
                Text(
                    text = "填報地點",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color(85,86,90),
                    textAlign = TextAlign.Start,
                )
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
                            text = Longitude.toString(),
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
                            text = Latitude.toString(),
                            color = Color(163,76,60),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 14.dp, bottom = 5.dp)
            )
            {
                Text(
                    text = "填報內容",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color(85,86,90),
                    textAlign = TextAlign.Start,
                )
            }

            TextField(
                modifier = Modifier
                    .size(2000.dp, 180.dp)
                    .fillMaxSize(),
                value = reportContentValue,
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
                    reportContentValue = it
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
                    .padding(vertical = 8.dp)
            )
            {
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(86, 107, 183)),
                    shape = RoundedCornerShape(50),
                    elevation = null,
                    onClick = {

                    },
                )
                {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
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
    Row(
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp, bottom = 10.dp, start = 50.dp, end = 50.dp)
    )
    {
        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(86, 107, 183)),
            elevation = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            onClick = {
                MA3_1_1_Buttombtn1_Upload(Login_UserId,
                    WorkTime.toString(),
                    WorkCode.toString(),
                    Longitude.toString(),
                    Latitude.toString(),
                    reportContentValue,
                    "/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAMCAgMCAgMDAwMEAwMEBQgFBQQEBQoHBwYIDAoMDAsKCwsNDhIQDQ4RDgsLEBYQERMUFRUVDA8XGBYUGBIUFRT/2wBDAQMEBAUEBQkFBQkUDQsNFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBT/wAARCAAZABoDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDyz9in9j9f2idS1PxD4mu59I8AaEc3l1FhXunA3NEjEHaAoyzYOARjk5H3l4W8L/ss3GiWVvoPg3QdW0m4XbHfHTjIrjOCWklG49OvP1qh/wAE92sp/wBiXy7IK1wG1JblU5bzctjOO+0p+GK+S9U8YanpPi7TtOhe7SwwFupEYqkTSZEQPuxXt0465r3spyP+1KMqkaqg4uK1V97+a2/I+YznH4nB8kcNa7u3dX0XTdbne/tv/wDBP7RPCHhC9+I/wvhkt9Ns18/U9DDmREizzNATyAucshJwMkYxivzy4/u/pX7Nfs+eIJ/E37Gvie78SXLXdukOt20rTnOIY3mQD8FAFfjMygsSDxnivnsZRWHrSpp3s2vuPoKVT2tONS1rq59K/sW/tiXn7L/iW7tNStptW8F6s6G+soSPNgkHAniDEDdg4K5G4AcjAr9AP+Gmv2W/F2k6zc3XiTSY4dbkjm1C3vLeeOWR0VFQ425UqI0xt7jPXJr8aV/oab/e/wA96541ZQ2NWk9z7w/a2/be8Kaj4Dv/AIX/AAZtXtPDupTTTavqzRtGJzLKZZUhDHdh3LbmYDIJAGDmvhHIpn8P/AadUSk5O7Ef/9k=");
            }
        ) {
            Text(
                text = "送出",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White
            )
        }
    }
}

fun MA3_1_1_Buttombtn1_Upload(UserID:String,WorkTime:String,WorkCode:String,Longitude:String,Latitude:String,InputContent:String,ImagePhoto:String){
    GlobalScope.launch(Dispatchers.IO) {
        var RequestJsonObject = JSONObject();
        RequestJsonObject.put("Function", "LocateFormUpload")
        RequestJsonObject.put("UserID", UserID)
        RequestJsonObject.put("WorkTime", WorkTime)
        RequestJsonObject.put("WorkCode", WorkCode)
        RequestJsonObject.put("Longitude", Longitude)
        RequestJsonObject.put("Latitude", Latitude)
        RequestJsonObject.put("InputContent", InputContent)
        RequestJsonObject.put("ImagePhoto", ImagePhoto)
        val responseString = HttpRequestTest(RequestJsonObject)
        Log.d("MA3_1_1_Buttombtn1",responseString)
        if(responseString!="Error"){
            var gson = Gson();
            var Response:LocateFormUpload_Response = gson.fromJson(responseString,LocateFormUpload_Response::class.java)
            if(Response.Feedback == "TRUE"){
                //TODO:跳轉回上頁
            }
        }
    }
}