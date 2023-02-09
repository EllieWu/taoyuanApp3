package com.elliewu.taoyuanapp3

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
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
import androidx.core.app.ActivityCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.common.internal.service.Common
import com.google.android.gms.maps.LocationSource
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.Button
import android.app.Activity.RESULT_OK
import android.app.Application
import android.content.Context
import android.content.IntentSender
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.runtime.*
import androidx.compose.ui.node.modifierElementOf
import androidx.compose.ui.zIndex
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.maps.android.compose.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.shareIn
import kotlin.random.Random
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import org.json.JSONObject

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
var redDotIsVis by mutableStateOf(true)
var blueDotIsVis by mutableStateOf(true)


@Preview(device = Devices.PIXEL_C)
@Preview(device = Devices.PIXEL_3A)
@Composable
fun MA3_1_1(WorkCode: String? = "",WorkTime: String?="",navController: NavHostController = rememberNavController()){
    MA3_1_1_RedPoint_MakeListCom(WorkCode.toString(),WorkTime.toString())
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
                    navController.navigate(Screen.MA3_1.route)
                },
                modifier = Modifier.padding(start = 20.dp)
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
                    infoLayout(navController, WorkCode.toString())
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
                            // Precise location access granted.
                        }
                        permissions.getOrDefault(
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            false
                        ) -> {
                            // Only approximate location access granted.
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

                //實驗區
                val fusedLocationClient = LocationServices.getFusedLocationProviderClient(
                    LocalContext.current)


                //實驗區-end
                val locationSource = MyLocationSource()

                val taiwan = LatLng(25.17403, 121.40338) //Param(緯度,經度) 南北緯 & 東西經 以正負號表示
                val cameraPositionState = rememberCameraPositionState {
                    position = CameraPosition.fromLatLngZoom(taiwan, 8f) //zoom 放大參數 數字越則越放大
                }
                //val taiwanMarker = rememberMarkerState(position = taiwan)
                GoogleMap(
                    modifier = Modifier
                        .height(650.dp)
                        .fillMaxHeight()
                        .zIndex(0f),
                    cameraPositionState = cameraPositionState,
                    locationSource = locationSource,
                ) {
                    redDotList.forEach{ item ->
                        Marker(
                            state = MarkerState(position = item),
                            visible = redDotIsVis
                        )
                    }

                    repairDotFakedata.forEach{ item ->
                        Marker(
                            icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE),
                            state = MarkerState(position = item),
                            visible = blueDotIsVis
                        )
                    }
                }
            }
            //Maps_End
            BottomSpace2(navController);
        }
    }
}



private class MyLocationSource : LocationSource {

    private var listener: LocationSource.OnLocationChangedListener? = null

    override fun activate(listener: LocationSource.OnLocationChangedListener) {
        this.listener = listener
    }

    override fun deactivate() {
        listener = null
    }

    fun onLocationChanged(location: Location) {
        listener?.onLocationChanged(location)
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