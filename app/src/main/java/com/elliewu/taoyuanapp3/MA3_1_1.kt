package com.elliewu.taoyuanapp3

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
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
import androidx.compose.ui.zIndex
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.LocationSource
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import com.google.maps.android.compose.*
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.shareIn
import org.json.JSONObject
import kotlin.random.Random
import kotlinx.coroutines.*


//data class LocationDetails(val longitude : String, val latitude : String)

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


//var fusedLocationClient:FusedLocationProviderClient? = null;
//var currentLocation:LocationDetails = LocationDetails("","")
//var locationCallback = object : LocationCallback() {
//    override fun onLocationResult(p0: LocationResult) {
//        for (lo in p0.locations) {
//            // Update UI with location data
//            currentLocation = LocationDetails(lo.latitude.toString(), lo.longitude.toString())
//        }
//    }
//}
//
//var locationRequired:Boolean = false
//fusedLocationClient = LocationServices.getFusedLocationProviderClient(
//        LocalContext.current)
//

//var mFusedLocationClient: FusedLocationProviderClient? = null



@Preview(device = Devices.PIXEL_C)
@Preview(device = Devices.PIXEL_3A)
@Composable
fun MA3_1_1(WorkCode: String? = "",WorkTime: String?="",navController: NavHostController = rememberNavController()){
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
//                var locationListener =  LocationListener(){
//                    fun onLocationChange(location: Location){
//                        if (location != null){
//
//                        }
//                    }
//                }
//            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(LocalContext.current);
//                mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
//                Context.getLocationManager() = getSystemService(Context.LOCATION_SERVICE) as LocationManager
//               val locationProvider = LocationManager.NETWORK_PROVIDER
///               var mLocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
//                mLocationManager.requestLocationUpdates(locationProvider, 1000, 10, locationListener);
                //實驗區-end
                val locationSource = MyLocationSource()

                val taiwan = LatLng(25.17403, 121.40338) //Param(緯度,經度) 南北緯 & 東西經 以正負號表示
                val cameraPositionState = rememberCameraPositionState {
                    position = CameraPosition.fromLatLngZoom(taiwan, 8f) //zoom 放大參數 數字越則越放大
                }
                con = LocalContext.current
                // To show blue dot on map
                val mapProperties by remember { mutableStateOf(MapProperties(isMyLocationEnabled = true)) }
                // Collect location updates

                val locationState = locationFlow.collectAsState(initial = newLocation())

                // Update blue dot and camera when the location changes
                LaunchedEffect(locationState.value) {
                    //Log.d(TAG, "Updating blue dot on map...")
                    locationSource.onLocationChanged(locationState.value)

                    //Log.d(TAG, "Updating camera position...")
                    //val cameraPosition = CameraPosition.fromLatLngZoom(LatLng(locationState.value.latitude, locationState.value.longitude), 8f)
                    //cameraPositionState.animate(CameraUpdateFactory.newCameraPosition(cameraPosition), 1_000)
                }
                GoogleMap(
                    modifier = Modifier
                        .height(650.dp)
                        .fillMaxHeight()
                        .zIndex(0f),
                    cameraPositionState = cameraPositionState,
                    locationSource = locationSource,
                    properties = mapProperties
                ) {
                    redDotList.forEach{ item ->
                        Marker(
                            state = MarkerState(position = item),
                            visible = redDotIsVis
                        )
                    }

                    blueDotList.forEach{ item ->
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

private val locationFlow = callbackFlow {
    while (true) {
        //++counter

        val location = newLocation()
        //Log.d(TAG, "Location $counter: $location")
        trySend(location)

        delay(2_000)
    }
}

private fun newLocation(): Location {
    val location = Location("MyLocationProvider")
    val taiwan = LatLng(25.17403, 121.40338)
    lateinit var fusedLocationClient: FusedLocationProviderClient
    fusedLocationClient = LocationServices.getFusedLocationProviderClient(con!!)
    if (ActivityCompat.checkSelfPermission(
            con!!,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            con!!,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        // TODO: Consider calling
        //    ActivityCompat#requestPermissions
        // here to request the missing permissions, and then overriding
        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
        //                                          int[] grantResults)
        // to handle the case where the user grants the permission. See the documentation
        // for ActivityCompat#requestPermissions for more details.

    }
    fusedLocationClient.lastLocation
        .addOnSuccessListener { location : Location? ->
            // Got last known location. In some rare situations this can be null.
            location?.apply {
                latitude = location.latitude
                longitude = location.longitude
            }

    }
    return location
}


//@SuppressLint("MissingPermission")
// private fun startLocationUpdates() {
//    locationCallback?.let {
//        val locationRequest = LocationRequest.create().apply {
//            interval = 10000
//            fastestInterval = 5000
//            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
//        }
//        fusedLocationClient?.requestLocationUpdates(
//            locationRequest,
//            it,
//            Looper.getMainLooper()
//        )
//    }
//}



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