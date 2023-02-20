package com.elliewu.taoyuanapp3

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.media.Image
import android.util.Base64
import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
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
data class WorkPointList(
    var Longitude: String,
    var Latitude: String,
    var DateTime: String,
    var InputContent: String,
    var ImagePhoto: String
)
var MA3_1_1_WorkPointList by mutableStateOf(
    WorkPointList("","","","",
        "/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAMCAgMCAgMDAwMEAwMEBQgFBQQEBQoHBwYIDAoMDAsKCwsNDhIQDQ4RDgsLEBYQERMUFRUVDA8XGBYUGBIUFRT/2wBDAQMEBAUEBQkFBQkUDQsNFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBT/wAARCAAZABoDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDyz9in9j9f2idS1PxD4mu59I8AaEc3l1FhXunA3NEjEHaAoyzYOARjk5H3l4W8L/ss3GiWVvoPg3QdW0m4XbHfHTjIrjOCWklG49OvP1qh/wAE92sp/wBiXy7IK1wG1JblU5bzctjOO+0p+GK+S9U8YanpPi7TtOhe7SwwFupEYqkTSZEQPuxXt0465r3spyP+1KMqkaqg4uK1V97+a2/I+YznH4nB8kcNa7u3dX0XTdbne/tv/wDBP7RPCHhC9+I/wvhkt9Ns18/U9DDmREizzNATyAucshJwMkYxivzy4/u/pX7Nfs+eIJ/E37Gvie78SXLXdukOt20rTnOIY3mQD8FAFfjMygsSDxnivnsZRWHrSpp3s2vuPoKVT2tONS1rq59K/sW/tiXn7L/iW7tNStptW8F6s6G+soSPNgkHAniDEDdg4K5G4AcjAr9AP+Gmv2W/F2k6zc3XiTSY4dbkjm1C3vLeeOWR0VFQ425UqI0xt7jPXJr8aV/oab/e/wA96541ZQ2NWk9z7w/a2/be8Kaj4Dv/AIX/AAZtXtPDupTTTavqzRtGJzLKZZUhDHdh3LbmYDIJAGDmvhHIpn8P/AadUSk5O7Ef/9k=")
)
@Preview(device = Devices.PIXEL_C)
@Preview(device = Devices.PIXEL_3A)
@Composable
fun MA3_1_1_WorkPoint(WorkTime:String?="",
                      WorkCode:String?="",
                      Longitude:String?="",
                      Latitude:String?="",
                      LocateNumber:String?="",
                      navController: NavHostController = rememberNavController()){
    var workContentValue by remember { mutableStateOf("") }
    workContentValue = MA3_1_1_WorkPointList.InputContent
    //Log.d("WorkTime",WorkTime.toString())
    //Log.d("WorkCode",WorkCode.toString())
    //Log.d("LocateNumber",LocateNumber.toString())
    MA3_1_1_WorkPoint_MakeListCom(WorkCode.toString(),WorkTime.toString(),LocateNumber.toString())
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
                    val MA3_1_1_fullRoutePath = Screen.MA3_1_1.route + "?WorkCode=${WorkCode}&WorkTime=${WorkTime}"
                    navController.navigate(MA3_1_1_fullRoutePath)
//                    navController.navigate(Screen.MA3_1_1.route)
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
                    .padding(top = 8.dp, bottom = 13.dp)
            )
            {
                Text(
                    text = "打卡時間:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color(85,86,90),
                    textAlign = TextAlign.Start,
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp), horizontalArrangement = Arrangement.Start
                )
                {
                    Text(
                        text = MA3_1_1_WorkPointList.DateTime,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,

                        color = Color(103, 103, 103),
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
                    text = "填報地點",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color(85,86,90),
                    textAlign = TextAlign.Start,
                )
            }
            Row(modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(7.dp))
                .background(Color.White, shape = RoundedCornerShape(7.dp))
                .padding(vertical = 10.dp, horizontal = 10.dp)){
                Column() {
                    Row(verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 8.dp, bottom = 10.dp)
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
                        modifier = Modifier.padding(bottom = 8.dp, top = 10.dp))
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
                value = workContentValue,
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
                    workContentValue = it
                    MA3_1_1_WorkPointList.InputContent = it
                },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = true, keyboardType = KeyboardType.Text, imeAction = ImeAction.Done
                ),
            )
            CameraTest_Jeremy(context = LocalContext.current)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            {
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(170,170,170)),
                    shape = RoundedCornerShape(50),
                    elevation = null,
                    onClick = {
                        AlertDialogState = true
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
                            text = "更換照片",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                        )
                    }
                }
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp), horizontalArrangement = Arrangement.Start)
            {
                var imageBytes = Base64.decode("", 0)
                if(CurrentPhoto != "")
                    imageBytes = Base64.decode(CurrentPhoto, 0)
                else
                    imageBytes = Base64.decode(MA3_1_1_WorkPointList.ImagePhoto, 0)
                val image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                if(image != null)
                {
                    Image(
                        modifier = Modifier.size(350.dp),
                        contentScale = ContentScale.FillWidth,
                        bitmap = image.asImageBitmap(),
                        contentDescription = "contentDescription"
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 10.dp, bottom = 10.dp, start = 80.dp, end = 80.dp)
            )
            {
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(86, 107, 183)),
                    elevation = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp),
                    onClick = {
                        GlobalScope.launch(Dispatchers.Main) {
                            var RequestJsonObject = JSONObject();
                            RequestJsonObject.put("Function", "LocateFormUploadAgain")
                            RequestJsonObject.put("UserID", Login_UserId)
                            RequestJsonObject.put("WorkTime", WorkTime)
                            RequestJsonObject.put("WorkCode", WorkCode)
                            RequestJsonObject.put("LocateNumber", LocateNumber)
                            RequestJsonObject.put("InputContent", MA3_1_1_WorkPointList.InputContent)
                            if(CurrentPhoto != "")
                                RequestJsonObject.put("ImagePhoto", CurrentPhoto)
                            else
                                RequestJsonObject.put("ImagePhoto", MA3_1_1_WorkPointList.ImagePhoto)

                            val responseString = HttpRequestTest(RequestJsonObject)
                            //Log.d("MA3_1_1_WorkPoint",responseString)
                            if(responseString!="Error"){
                                var gson = Gson();
                                var WorkInfoResponse:LocateFormUploadAgain_Response = gson.fromJson(responseString,LocateFormUploadAgain_Response::class.java)
                                if(WorkInfoResponse.Feedback == "TRUE")
                                {
                                    val fullpath = Screen.MA3_1_1.route + "?WorkCode=${WorkCode}&WorkTime=${WorkTime}"
                                    navController.navigate(fullpath)
                                    MA3_1_1_WorkPointList = WorkPointList("","","","",
                                        "/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAMCAgMCAgMDAwMEAwMEBQgFBQQEBQoHBwYIDAoMDAsKCwsNDhIQDQ4RDgsLEBYQERMUFRUVDA8XGBYUGBIUFRT/2wBDAQMEBAUEBQkFBQkUDQsNFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBT/wAARCAAZABoDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDyz9in9j9f2idS1PxD4mu59I8AaEc3l1FhXunA3NEjEHaAoyzYOARjk5H3l4W8L/ss3GiWVvoPg3QdW0m4XbHfHTjIrjOCWklG49OvP1qh/wAE92sp/wBiXy7IK1wG1JblU5bzctjOO+0p+GK+S9U8YanpPi7TtOhe7SwwFupEYqkTSZEQPuxXt0465r3spyP+1KMqkaqg4uK1V97+a2/I+YznH4nB8kcNa7u3dX0XTdbne/tv/wDBP7RPCHhC9+I/wvhkt9Ns18/U9DDmREizzNATyAucshJwMkYxivzy4/u/pX7Nfs+eIJ/E37Gvie78SXLXdukOt20rTnOIY3mQD8FAFfjMygsSDxnivnsZRWHrSpp3s2vuPoKVT2tONS1rq59K/sW/tiXn7L/iW7tNStptW8F6s6G+soSPNgkHAniDEDdg4K5G4AcjAr9AP+Gmv2W/F2k6zc3XiTSY4dbkjm1C3vLeeOWR0VFQ425UqI0xt7jPXJr8aV/oab/e/wA96541ZQ2NWk9z7w/a2/be8Kaj4Dv/AIX/AAZtXtPDupTTTavqzRtGJzLKZZUhDHdh3LbmYDIJAGDmvhHIpn8P/AadUSk5O7Ef/9k=")
                                    CurrentPhoto = ""
                                }
                            }
                        }
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

    }

}
//TODO:Jeremy增加
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MA3_1_1_WorkPoint_MakeListCom(WorkCode:String,WorkTime: String,LocateNumber:String){
    MA3_1_1_WorkPoint_MakeList(WorkCode,WorkTime,LocateNumber)
}
fun MA3_1_1_WorkPoint_MakeList(WorkCode:String,WorkTime: String,LocateNumber:String){
    GlobalScope.launch(Dispatchers.IO) {
        var RequestJsonObject = JSONObject();
        RequestJsonObject.put("Function", "RequestLocateInfo")
        RequestJsonObject.put("WorkCode", WorkCode)
        RequestJsonObject.put("WorkTime", WorkTime)
        RequestJsonObject.put("LocateNumber", LocateNumber)
        val responseString = HttpRequestTest(RequestJsonObject)
        //Log.d("MA3_1_1_WorkPoint",responseString)
        if(responseString!="Error"){
            var gson = Gson();
            var WorkInfoResponse:RequestLocateInfo_Response = gson.fromJson(responseString,RequestLocateInfo_Response::class.java)
            var workListDatas = WorkPointList("","","","",
                "/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAMCAgMCAgMDAwMEAwMEBQgFBQQEBQoHBwYIDAoMDAsKCwsNDhIQDQ4RDgsLEBYQERMUFRUVDA8XGBYUGBIUFRT/2wBDAQMEBAUEBQkFBQkUDQsNFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBT/wAARCAAZABoDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDyz9in9j9f2idS1PxD4mu59I8AaEc3l1FhXunA3NEjEHaAoyzYOARjk5H3l4W8L/ss3GiWVvoPg3QdW0m4XbHfHTjIrjOCWklG49OvP1qh/wAE92sp/wBiXy7IK1wG1JblU5bzctjOO+0p+GK+S9U8YanpPi7TtOhe7SwwFupEYqkTSZEQPuxXt0465r3spyP+1KMqkaqg4uK1V97+a2/I+YznH4nB8kcNa7u3dX0XTdbne/tv/wDBP7RPCHhC9+I/wvhkt9Ns18/U9DDmREizzNATyAucshJwMkYxivzy4/u/pX7Nfs+eIJ/E37Gvie78SXLXdukOt20rTnOIY3mQD8FAFfjMygsSDxnivnsZRWHrSpp3s2vuPoKVT2tONS1rq59K/sW/tiXn7L/iW7tNStptW8F6s6G+soSPNgkHAniDEDdg4K5G4AcjAr9AP+Gmv2W/F2k6zc3XiTSY4dbkjm1C3vLeeOWR0VFQ425UqI0xt7jPXJr8aV/oab/e/wA96541ZQ2NWk9z7w/a2/be8Kaj4Dv/AIX/AAZtXtPDupTTTavqzRtGJzLKZZUhDHdh3LbmYDIJAGDmvhHIpn8P/AadUSk5O7Ef/9k=")
            workListDatas.Longitude = WorkInfoResponse.Longitude.toString()
            workListDatas.Latitude = WorkInfoResponse.Latitude.toString()
            workListDatas.DateTime = WorkInfoResponse.DateTime.toString()
            workListDatas.InputContent = WorkInfoResponse.InputContent.toString()
            workListDatas.ImagePhoto = WorkInfoResponse.ImagePhoto.toString()
            MA3_1_1_WorkPointList = workListDatas
        }
    }
}