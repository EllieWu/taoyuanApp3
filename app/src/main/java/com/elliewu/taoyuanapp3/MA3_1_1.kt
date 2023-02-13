package com.elliewu.taoyuanapp3

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.maps.LocationSource
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.location.*
import androidx.compose.material.Button
import androidx.compose.foundation.Image
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.ui.node.modifierElementOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.zIndex
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.maps.android.compose.*
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.shareIn
import kotlin.random.Random
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.elliewu.taoyuanapp3.clusters.ZoneClusterManager
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLngBounds
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

val fakedata = listOf<LatLng>(
    LatLng(25.046296,121.506857),
    LatLng(25.056437,121.550903),
    LatLng(24.726862,121.744826),
    LatLng(24.245541,120.718384)
)
var redDotList by mutableStateOf(fakedata)
val repairDotFakedata = listOf<LatLng>(
    LatLng(23.588299,121.083543),
    LatLng(22.899511,120.395490),
    LatLng(22.874993,121.067168),
    LatLng(23.696732,121.459551)
)
var blueDotList by mutableStateOf(repairDotFakedata)

var redDotIsVis by mutableStateOf(true)
var blueDotIsVis by mutableStateOf(true)

//@Preview(device = Devices.PIXEL_C)
//@Preview(device = Devices.PIXEL_3A)

@Composable
fun MA3_1_1(
    state: MapState,
    setupClusterManager: (Context, GoogleMap) -> ZoneClusterManager,
    calculateZoneViewCenter: () -> LatLngBounds,
    fusedLocationProviderClient :FusedLocationProviderClient,
    viewModel: MapViewModel,
    WorkCode: String? = "", WorkTime: String?="", navController: NavHostController = rememberNavController()
){
    MA3_1_1_RedPoint_MakeListCom(WorkCode.toString(),WorkTime.toString())
    MA3_1_1_BluePoint_MakeListCom(MA3_1_date, Login_UserId);

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
                    navController.navigate(Screen.MA3_1.route)
                },
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(end = 40.dp),
                //size(width = 250.dp, height = 30.dp),

                text = "巡檢打卡填報",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = Color(255, 255, 255),
            )


        }
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
            ) {
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.zIndex(3f)) {
                    infoLayout(navController, WorkCode.toString(),WorkTime.toString())
                }

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
                        .height(650.dp)
                        .fillMaxHeight()
                        .zIndex(0f),
                    cameraPositionState = cameraPositionState,
                    properties = mapProperties,
                ) {
                    val context = LocalContext.current
                    redDotList.forEach{ item ->
                        MarkerInfoWindow(
                            icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED),
                            state = MarkerState(position = item),
                            visible = redDotIsVis,
                            title = "打卡",
                            snippet = "打卡點-1",
                        ) { marker ->
                            // Implement the custom info window here
                            Row(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .background(Color.White)
                                    .width(200.dp)
                                , verticalAlignment = Alignment.CenterVertically) {
                                Column(modifier = Modifier
                                    .padding(10.dp)
                                    .weight(0.55f)
                                ) {
                                    Text(marker.title ?: "Default Marker Title", color = Color.Black, fontSize = 24.sp)
                                    Text(marker.snippet ?: "Default Marker Snippet", color = Color.Black)
                                }
                                Button(
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = Color.Transparent
                                    ),
                                    onClick = {
                                    },
                                    modifier = Modifier.weight(0.45f)
                                )
                                {
                                    Image(
                                        painterResource(id = R.drawable.map_clockin2),
                                        contentDescription = "null",
                                        modifier = Modifier.weight(1f)
                                    )
                                }
                            }

                        }
                    }

                    blueDotList.forEach{ item ->
                        MarkerInfoWindow(
                            icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE),
                            state = MarkerState(position = item),
                            visible = blueDotIsVis,
                            title = "報修",
                            snippet = "報修點-1",
                        ) { marker ->
                            // Implement the custom info window here
                            Row(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .background(Color.White)
                                    .width(200.dp)
                                , verticalAlignment = Alignment.CenterVertically) {
                                Column(modifier = Modifier
                                    .padding(10.dp)
                                    .weight(0.55f)
                                    ) {
                                    Text(marker.title ?: "Default Marker Title", color = Color.Black, fontSize = 24.sp)
                                    Text(marker.snippet ?: "Default Marker Snippet", color = Color.Black)
                                }
                                Button(
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = Color.Transparent
                                    ),
                                    onClick = {
                                    },
                                    modifier = Modifier.weight(0.45f)
                                )
                                {
                                    Image(
                                        painterResource(id = R.drawable.map_repair2),
                                        contentDescription = "null",
                                        modifier = Modifier.weight(1f)
                                    )
                                }
                            }
                        }
                    }
                }
            }
//            Button(onClick = {
//                Log.d("緯度", state.lastKnownLocation?.latitude.toString())
//                Log.d("經度", state.lastKnownLocation?.longitude.toString())}) {
//
//            }
            //Maps_End
            BottomSpace2(navController,WorkTime,WorkCode,state.lastKnownLocation?.longitude.toString(),state.lastKnownLocation?.latitude.toString());
        }
    }
}


//TODO:Jeremy增加
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MA3_1_1_RedPoint_MakeListCom(WorkCode:String,WorkTime:String){
    MA3_1_1_RedPoint_MakeList(WorkCode,WorkTime)
}
fun MA3_1_1_RedPoint_MakeList(WorkCode:String,WorkTime:String){
    GlobalScope.launch(Dispatchers.IO) {
        var RequestJsonObject = JSONObject();
        RequestJsonObject.put("Function", "RequestLocate")
        RequestJsonObject.put("WorkCode", WorkCode)
        RequestJsonObject.put("WorkTime", WorkTime)
        val responseString = HttpRequestTest(RequestJsonObject)
        Log.d("MA3_1_1_CheckPoint",responseString)
        if(responseString!="Error"){
            var gson = Gson();
            var WorkInfoResponse:RequestLocate_Response = gson.fromJson(responseString,RequestLocate_Response::class.java)
            var workListDatas = listOf<LatLng>(
                LatLng(25.046296,121.506857))
            workListDatas = workListDatas - workListDatas[workListDatas.size - 1]
            if(WorkInfoResponse.Locate != null && WorkInfoResponse.Locate!!.isNotEmpty()){
                WorkInfoResponse.Locate!!.forEach {
                    workListDatas = workListDatas + LatLng(it.Latitude.toDouble(),it.Longitude.toDouble())
                }
            }
            redDotList = workListDatas
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MA3_1_1_BluePoint_MakeListCom(Date:String,UserID:String){
    MA3_1_1_BluePoint_MakeList(Date,UserID)
}
fun MA3_1_1_BluePoint_MakeList(Date:String,UserID:String){
    GlobalScope.launch(Dispatchers.IO) {
        var RequestJsonObject = JSONObject();
        RequestJsonObject.put("Function", "RequestRepairLocate")
        RequestJsonObject.put("Date", Date)
        RequestJsonObject.put("UserID", UserID)
        RequestJsonObject.put("ReportType", "外巡報修")
        val responseString = HttpRequestTest(RequestJsonObject)
        Log.d("MA3_1_1_Bluepoint",responseString)
        if(responseString!="Error"){
            var gson = Gson();
            var WorkInfoResponse:RequestRepairLocate_Response = gson.fromJson(responseString,RequestRepairLocate_Response::class.java)
            var workListDatas = listOf<LatLng>(
                LatLng(25.046296,121.506857))

            workListDatas = workListDatas - workListDatas[workListDatas.size - 1]

            if(WorkInfoResponse.RepairLocate != null && WorkInfoResponse.RepairLocate!!.isNotEmpty()){
                WorkInfoResponse.RepairLocate!!.forEach {
                    workListDatas = workListDatas + LatLng(it.Latitude.toDouble(),it.Longitude.toDouble())

                }
            }
            blueDotList = workListDatas
        }
    }
}

/**
 * If you want to center on a specific location.
 */
private suspend fun CameraPositionState.centerOnLocation(
    location: Location
) = animate(
    update = CameraUpdateFactory.newLatLngZoom(
        LatLng(location.latitude, location.longitude),
        15f
    ),
)