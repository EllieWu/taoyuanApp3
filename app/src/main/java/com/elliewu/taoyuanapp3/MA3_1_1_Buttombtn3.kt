package com.elliewu.taoyuanapp3

import android.Manifest
import android.content.Context
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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

    var titleValue by remember { mutableStateOf("") }
    var reportContentValue by remember {
        mutableStateOf("")
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
        val taiwan = LatLng(25.17403, 121.40338) //Param(??????,??????) ????????? & ????????? ??????????????????
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(taiwan, 8f) //zoom ???????????? ?????????????????????
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

//            ClickableText(
//                text = AnnotatedString("??????"),
//                style = TextStyle(
//                    color = Color.White,
//                    fontSize = 20.sp,
//                    textAlign = TextAlign.End,
//                ),
//                onClick = {
//                    //navController.navigate(Screen.MA3_1_1.route)
//                    val MA3_1_1_fullRoutePath = Screen.MA3_1_1.route + "?WorkCode=${WorkCode.toString()}&WorkTime=${WorkTime.toString()}"
//                    if(WorkCode !="" && WorkCode != null && WorkTime!="" && WorkTime != null)
//                        navController.navigate(MA3_1_1_fullRoutePath)
//                    else
//                        navController.navigate(Screen.MA3_3.route)
//                }
//            )
            Button(
                elevation = null,
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent
                ),
                onClick = {
                    val MA3_1_1_fullRoutePath = Screen.MA3_1_1.route + "?WorkCode=${WorkCode.toString()}&WorkTime=${WorkTime.toString()}"
                    if(WorkCode !="" && WorkCode != null && WorkTime!="" && WorkTime != null)
                    {
                        navController.navigate(MA3_1_1_fullRoutePath)
                        CurrentPhoto = ""
                    }
                    else
                    {
                        navController.navigate(Screen.MA3_3.route)
                        CurrentPhoto = ""
                    }
                }
            ) {
                Icon(
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .size(30.dp),
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "BackIcon",
                    tint = Color.White
                )
                Text(
                    text = "??????",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 20.sp,
                        textAlign = TextAlign.End,
                    )
                )
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(end = 40.dp),
                //size(width = 250.dp, height = 30.dp),

                text = "????????????",
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
                    text = "????????????",
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
                    text = "????????????",
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
                            var fullpath = Screen.MA3_1_1_RepairDotPreview.route + "?Longitude=${btn3_longitude}&Latitude=${btn3_latitude}&WorkCode=${WorkCode.toString()}&WorkTime=${WorkTime.toString()}"
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
                            //Log.d("longitude",state.lastKnownLocation?.longitude.toString())
                            //Log.d("latitude",state.lastKnownLocation?.latitude.toString())
//                            val fullpath = Screen.MA3_1_1_Bottombtn3.route + "?WorkCode=${WorkCode}&WorkTime=${WorkTime}"
//                            navController.navigate(fullpath)
                        }
                    ) {
                        Text(
                            modifier = Modifier.padding(horizontal = 8.dp),
                            text = "????????????",
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
                            text = "GIS X??????:",
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
                            text = "GIS Y??????:",
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
                    text = "????????????",
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
            CameraTest_Jeremy(context = LocalContext.current)
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
                                .size(25.dp)
                                .padding(end = 5.dp),
                            painter = painterResource(id = R.drawable.camera),
                            contentDescription = "BackIcon",
                            tint = Color.White
                        )
                        if(CurrentPhoto == ""){
                            Text(
                                text = "??????",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                            )
                        }
                        else{
                            Text(
                                text = "????????????",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                            )
                        }
                    }
                }
            }

            //??????????????????
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp), horizontalArrangement = Arrangement.Start)
            {
                val imageBytes = Base64.decode(CurrentPhoto, 0)
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
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(84, 117, 162)),
                    elevation = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp),
                    onClick = {
                        GlobalScope.launch(Dispatchers.IO) {
                            var RequestJsonObject = JSONObject();
                            RequestJsonObject.put("Function", "NewReportUpload")
                            RequestJsonObject.put("UserID", Login_UserId)
                            RequestJsonObject.put("Longitude", btn3_longitude)
                            RequestJsonObject.put("Latitude", btn3_latitude)
                            RequestJsonObject.put("ReportTitle", titleValue)
                            RequestJsonObject.put("ReportContent", reportContentValue)
                            RequestJsonObject.put("ReportPhoto", CurrentPhoto)
                            RequestJsonObject.put("ReportType", "????????????")

                            val responseString = HttpRequestTest(RequestJsonObject)
                            //Log.d("MA3_1_1_Buttombtn3",responseString)
                            if(responseString!="Error"){
                                var gson = Gson();
                                var Response:LocateFormUpload_Response = gson.fromJson(responseString,LocateFormUpload_Response::class.java)
                                if(Response.Feedback == "TRUE"){
                                    GlobalScope.launch(Dispatchers.Main) {
                                        val MA3_1_1_fullRoutePath = Screen.MA3_1_1.route + "?WorkCode=${WorkCode.toString()}&WorkTime=${WorkTime.toString()}"
                                        if(WorkCode !="" && WorkCode != null && WorkTime!="" && WorkTime != null)
                                        {

                                            navController.navigate(MA3_1_1_fullRoutePath)
                                            CurrentPhoto = ""
                                        }
                                        else
                                        {
                                            navController.navigate(Screen.MA3_3.route)
                                            CurrentPhoto = ""
                                        }
                                    }
                                }
                            }
                        }
                    }
                ) {
                    Text(
                        text = "??????",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.White
                    )
                }
            }
        }
    }

}