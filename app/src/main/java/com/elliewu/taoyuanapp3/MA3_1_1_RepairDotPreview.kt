package com.elliewu.taoyuanapp3

import android.Manifest
import android.content.Context
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.elliewu.taoyuanapp3.clusters.ZoneClusterManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.*
import com.google.maps.android.ktx.model.cameraPosition

//@Preview
@Composable
fun MA3_1_1_RepairDotPreview(state: MapState,
            fusedLocationProviderClient : FusedLocationProviderClient,
            viewModel: MapViewModel,
            navController: NavHostController = rememberNavController(),
            longitude:String?,
            latitude:String?,
//            repairTitle: String?
) {
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
                    //navController.navigate(Screen.MA3_2_1.route)
                    var fullMA3_2_1_path =
                        Screen.MA3_2_1.route
                    Log.d("返回", "MA3_2_2: ")
                    navController.navigate(fullMA3_2_1_path)
                },
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                //size(width = 250.dp, height = 30.dp),
                text = "地圖",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = Color(255, 255, 255),
            )
        }
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
        var repairLocation = LatLng(25.17403, 121.40338);
        if(latitude!=null&&longitude!=null){
            repairLocation = LatLng(latitude.toDouble(),longitude.toDouble()) //Param(緯度,經度) 南北緯 & 東西經 以正負號表示
        }

        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(repairLocation, 15f) //zoom 放大參數 數字越則越放大
        }
        GoogleMap(
            modifier = Modifier.fillMaxWidth(),
            cameraPositionState = cameraPositionState,
            properties = mapProperties
        )
        {
            if(latitude!=null&&longitude!=null){
                Marker(
                    icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED),
                    state = MarkerState(position = LatLng(latitude!!.toDouble(),longitude!!.toDouble())),
                    //title = repairTitle
                ){

                }
            }

        }
        //Map_End
    }
}