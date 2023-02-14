package com.elliewu.taoyuanapp3

import android.Manifest
import android.content.Context
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.elliewu.taoyuanapp3.clusters.ZoneClusterManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.gson.Gson
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject

//@Preview(device = Devices.PIXEL_C)
//@Preview(device = Devices.PIXEL_3A)
@Composable

fun MA3_1_1_Bottombtn3(
    state: MapState,
    fusedLocationProviderClient : FusedLocationProviderClient,
    viewModel: MapViewModel,
    WorkCode: String? = "", WorkTime: String?="", navController: NavHostController = rememberNavController()
){

    var titleValue by remember { mutableStateOf("報修主旨") }
    var reportContentValue by remember {
        mutableStateOf("報修內容")
    }
    var btn3_longitude by remember{ mutableStateOf("")}
    var btn3_latitude by remember{ mutableStateOf("")}
    btn3_longitude = state.lastKnownLocation?.longitude.toString();
    btn3_latitude = state.lastKnownLocation?.latitude.toString();
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(232, 236, 247)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Maps_start
        val locationPermissionRequest = rememberLauncherForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    false
                ) -> {
                    viewModel.getDeviceLocation(fusedLocationProviderClient)
                    // Precise location access granted.
                }
                permissions.getOrDefault(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    false
                ) -> {
                    // Only approximate location access granted.
                    viewModel.getDeviceLocation(fusedLocationProviderClient)
                }
                else -> {
                    // No location access granted.
                }
            }
        }

        SideEffect {
            locationPermissionRequest.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
        // Set properties using MapProperties which you can use to recompose the map\
        val mapProperties = MapProperties(
            // Only enable if user has accepted location permissions.
            isMyLocationEnabled = state.lastKnownLocation != null,
        )
        val taiwan = LatLng(25.17403, 121.40338) //Param(緯度,經度) 南北緯 & 東西經 以正負號表示
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(taiwan, 8f) //zoom 放大參數 數字越則越放大
        }
        GoogleMap(
            modifier = Modifier
                .height(0.dp)
                .fillMaxHeight()
                .zIndex(0f),
            cameraPositionState = cameraPositionState,
            properties = mapProperties,
        )
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
                    val MA3_1_1_fullRoutePath = Screen.MA3_1_1.route + "?WorkCode=${WorkCode.toString()}&WorkTime=${WorkTime.toString()}"
                    if(WorkCode !="" && WorkCode != null && WorkTime!="" && WorkTime != null)
                        navController.navigate(MA3_1_1_fullRoutePath)
                    else
                        navController.navigate(Screen.MA3_3.route)
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
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp, bottom = 0.dp, start = 20.dp, end = 20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            {
                Text(
                    text = "報修主旨",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color(85, 86, 90)
                )
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
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Spacer(modifier = Modifier.width(width = 8.dp))
                            innerTextField()
                        }
                    })
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            {
                Text(
                    text = "報修地點",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color(85, 86, 90)
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Button(
                        elevation = null,
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Transparent
                        ),
                        onClick = {
                            var fullpath = Screen.MA3_1_1_RepairDotPreview.route + "?longitude=${btn3_longitude}&latitude=${btn3_latitude}&WorkCode=${WorkCode.toString()}&WorkTime=${WorkTime.toString()}"
                            navController.navigate(fullpath) })
                    {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "Favorite icon",
                            tint = Color(64, 111, 158)
                        )
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(84, 117, 162)
                        ),
                        elevation = null,
                        //modifier = Modifier.padding(start = 82.dp),
                        contentPadding = PaddingValues(0.dp),
                        onClick = {
                            viewModel.getDeviceLocation(fusedLocationProviderClient)
                            btn3_longitude = state.lastKnownLocation?.longitude.toString();
                            btn3_latitude = state.lastKnownLocation?.latitude.toString();
                            Log.d("longitude",state.lastKnownLocation?.longitude.toString())
                            Log.d("latitude",state.lastKnownLocation?.latitude.toString())
//                            val fullpath = Screen.MA3_1_1_Bottombtn3.route + "?WorkCode=${WorkCode}&WorkTime=${WorkTime}"
//                            navController.navigate(fullpath)
                        }
                    ) {
                        Text(
                            modifier = Modifier.padding(horizontal = 8.dp),
                            text = "重新定位",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color(255, 255, 255),
                            textDecoration = TextDecoration.Underline,
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = RoundedCornerShape(7.dp))
                    .background(Color.White, shape = RoundedCornerShape(7.dp))
                    .padding(vertical = 10.dp, horizontal = 10.dp)
            ) {
                Column() {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 5.dp, bottom = 13.dp)
                    )
                    {
                        Text(
                            text = "GIS X座標:",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(128, 127, 129)
                        )
                        Text(
                            modifier = Modifier.padding(start = 25.dp),
                            text = btn3_longitude,
                            color = Color(64,74,135),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 5.dp)
                    )
                    {
                        Text(
                            text = "GIS Y座標:",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(128, 127, 129)
                        )
                        Text(
                            modifier = Modifier.padding(start = 25.dp),
                            text = btn3_latitude,
                            color = Color(64,74,135),
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
                    fontSize = 18.sp,
                    color = Color(85, 86, 90),
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
                    colors = ButtonDefaults.buttonColors
                        (backgroundColor = Color(84, 117, 162)),
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
                                .size(25.dp).padding(end = 5.dp),
                            painter = painterResource(id = R.drawable.camera),
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

            //照片顯示位置
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp), horizontalArrangement = Arrangement.Start)
            {
//                    val imageBytes = Base64.decode(list.RepairPhoto, 0)
//                    val image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
//                    if(image != null)
//                    {
//                        Image(
//                            modifier = Modifier.size(350.dp),
//                            contentScale = ContentScale.FillWidth,
//                            bitmap = image.asImageBitmap(),
//                            contentDescription = "contentDescription"
//                        )
//                    }
            }
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
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(84, 117, 162)),
            elevation = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            onClick = {
                GlobalScope.launch(Dispatchers.Main) {
                    var RequestJsonObject = JSONObject();
                    RequestJsonObject.put("Function", "NewReportUpload")
                    RequestJsonObject.put("UserID", Login_UserId)
                    RequestJsonObject.put("Longitude", btn3_longitude)
                    RequestJsonObject.put("Latitude", btn3_latitude)
                    RequestJsonObject.put("ReportTitle", titleValue)
                    RequestJsonObject.put("ReportContent", reportContentValue)
                    RequestJsonObject.put("ReportPhoto", "/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAMCAgMCAgMDAwMEAwMEBQgFBQQEBQoHBwYIDAoMDAsKCwsNDhIQDQ4RDgsLEBYQERMUFRUVDA8XGBYUGBIUFRT/2wBDAQMEBAUEBQkFBQkUDQsNFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBT/wAARCAAZABoDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDyz9in9j9f2idS1PxD4mu59I8AaEc3l1FhXunA3NEjEHaAoyzYOARjk5H3l4W8L/ss3GiWVvoPg3QdW0m4XbHfHTjIrjOCWklG49OvP1qh/wAE92sp/wBiXy7IK1wG1JblU5bzctjOO+0p+GK+S9U8YanpPi7TtOhe7SwwFupEYqkTSZEQPuxXt0465r3spyP+1KMqkaqg4uK1V97+a2/I+YznH4nB8kcNa7u3dX0XTdbne/tv/wDBP7RPCHhC9+I/wvhkt9Ns18/U9DDmREizzNATyAucshJwMkYxivzy4/u/pX7Nfs+eIJ/E37Gvie78SXLXdukOt20rTnOIY3mQD8FAFfjMygsSDxnivnsZRWHrSpp3s2vuPoKVT2tONS1rq59K/sW/tiXn7L/iW7tNStptW8F6s6G+soSPNgkHAniDEDdg4K5G4AcjAr9AP+Gmv2W/F2k6zc3XiTSY4dbkjm1C3vLeeOWR0VFQ425UqI0xt7jPXJr8aV/oab/e/wA96541ZQ2NWk9z7w/a2/be8Kaj4Dv/AIX/AAZtXtPDupTTTavqzRtGJzLKZZUhDHdh3LbmYDIJAGDmvhHIpn8P/AadUSk5O7Ef/9k=")
                    RequestJsonObject.put("ReportType", "外巡報修")

                    val responseString = HttpRequestTest(RequestJsonObject)
                    Log.d("MA3_1_1_Buttombtn3",responseString)
                    if(responseString!="Error"){
                        var gson = Gson();
                        var Response:LocateFormUpload_Response = gson.fromJson(responseString,LocateFormUpload_Response::class.java)
                        if(Response.Feedback == "TRUE"){
                            //TODO:跳轉回首頁
//                            val MA3_1_1_fullRoutePath = Screen.MA3_1_1.route + "?WorkCode=${WorkCode}&WorkTime=${WorkTime}"
//                            navController.navigate(MA3_1_1_fullRoutePath)
                            navController.navigate(Screen.MA3_1.route)
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