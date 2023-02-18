package com.elliewu.taoyuanapp3

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentDialog
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
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
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*



var AlertDialogState by mutableStateOf(false);
@Preview(device = Devices.PIXEL_C)
@Preview(device = Devices.PIXEL_3A)
@Composable
fun MA3_1_1_Buttonbtn1(WorkTime:String?="",
                       WorkCode:String?="",
                       Longitude:String?="",
                       Latitude:String?="",
                       navController: NavHostController = rememberNavController()){
    var reportContentValue by remember { mutableStateOf("") }
//    Log.d("WorkTime",WorkTime.toString())
//    Log.d("WorkCode",WorkCode.toString())
//    Log.d("Longitude",Longitude.toString())
//    Log.d("Latitude",Latitude.toString())

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
                    CurrentPhoto = ""
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
            val context = LocalContext.current
            CameraTest_Jeremy(context)
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
                        AlertDialogState = true
                    }
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
                                text = "拍照",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                            )
                        }
                        else{
                            Text(
                                text = "更換照片",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                            )
                        }
                    }
                }
            }
            //照片顯示位置
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
                    RequestJsonObject.put("Function", "LocateFormUpload")
                    RequestJsonObject.put("UserID", Login_UserId)
                    RequestJsonObject.put("WorkTime", WorkTime)
                    RequestJsonObject.put("WorkCode", WorkCode)
                    RequestJsonObject.put("Longitude", Longitude)
                    RequestJsonObject.put("Latitude", Latitude)
                    RequestJsonObject.put("InputContent", reportContentValue)
                    RequestJsonObject.put("ImagePhoto", CurrentPhoto)
                    val responseString = HttpRequestTest(RequestJsonObject)
                    Log.d("MA3_1_1_Buttombtn1",responseString)
                    if(responseString!="Error"){
                        var gson = Gson();
                        var Response:LocateFormUpload_Response = gson.fromJson(responseString,LocateFormUpload_Response::class.java)
                        if(Response.Feedback == "TRUE"){
                            //TODO:跳轉回上頁
                            val MA3_1_1_fullRoutePath = Screen.MA3_1_1.route + "?WorkCode=${WorkCode}&WorkTime=${WorkTime}"
                            navController.navigate(MA3_1_1_fullRoutePath)
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


fun Context.createImageFile(): File {
    // Create an image file name
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"
    val image = File.createTempFile(
        imageFileName, /* prefix */
        ".jpg", /* suffix */
        externalCacheDir      /* directory */
    )
    return image
}

@Composable
fun CameraTest_Jeremy(context:Context){
    val file = context.createImageFile()
    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(context), BuildConfig.APPLICATION_ID + ".provider", file
    )

    var capturedImageUri by remember { mutableStateOf(Uri.EMPTY) }
    var cameraCapturedImageUri by remember { mutableStateOf(Uri.EMPTY) }

    var imageBase64 by remember { mutableStateOf<String?>(null) }
    var fileExistsImageUriPath by remember { mutableStateOf<String?>(null) }
    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success) {
                cameraCapturedImageUri = uri
            }
        }

    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            if (uri != null) {
                capturedImageUri = uri
            }
        }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            cameraLauncher.launch(uri)
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }

    val imagePermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            galleryLauncher.launch("image/*")
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }
    if(AlertDialogState){
        AlertDialog(
            onDismissRequest = {
                AlertDialogState = false
            },

            title = {
                Text(
                    text = "選擇拍照或從相簿選取")
            },
            buttons = {
                Column(
                    modifier = Modifier.padding(10.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Button(
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(86, 107, 183)),
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .fillMaxWidth(),
                        onClick = {
                            val permissionCheckResult =
                                ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                            if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                                cameraLauncher.launch(uri)
                            } else {
                                permissionLauncher.launch(Manifest.permission.CAMERA)
                            }
                            AlertDialogState = false
                        }) {
                        Text(
                            text = "拍照",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(86, 107, 183)),
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .fillMaxWidth(),
                        onClick = {
                            val permissionCheckResult = ContextCompat.checkSelfPermission(
                                context, Manifest.permission.READ_MEDIA_IMAGES
                            )
                            if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                                galleryLauncher.launch("image/*")
                            } else {
                                imagePermissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
                            }
                            AlertDialogState = false
                        }) {
                        Text(
                            text = "相簿",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            },
        )
    }
    if (capturedImageUri?.path != null) {
        if (capturedImageUri.path?.isNotEmpty() == true) {
            imageBase64 = null
            fileExistsImageUriPath = null
            val contextOK: Context = context
            val uriOK: Uri = capturedImageUri
            val pathOK = getPathFromUri(contextOK, uriOK)
            if (pathOK != null) {
                fileExistsImageUriPath = pathOK
                val fileOK = File(pathOK)
                if (fileOK.exists()) {
                    imageBase64 = fileToBase64(fileOK)
                    val base64String = fileToBase64(fileOK)
                    CurrentPhoto = base64String
                }
            }
            if (capturedImageUri != null) {
                val inputStream =
                    LocalContext.current.contentResolver.openInputStream(capturedImageUri)
                val MinWenBitmap = BitmapFactory.decodeStream(inputStream)
                val bitmap: Bitmap = MinWenBitmap //BitmapFactory.decodeStream(inputStream)
                val imageFileName =
                    "JPEG_" + SimpleDateFormat("yyyyMMdd_HHmmss").format(Date()) + "_myImage.jpg"
                val dir =
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM + "/Camera")
                val file = File(dir, imageFileName)
                val fos = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
                fos.flush()
                fos.close()
                cameraCapturedImageUri = null
            }
        }
    }
    if (cameraCapturedImageUri?.path != null) {
        imageBase64 = null
        fileExistsImageUriPath = null
        val contextOK: Context = context
        val uriOK: Uri = cameraCapturedImageUri
        val pathOK = getPathFromUri(contextOK, uriOK)
        if (pathOK != null) {
            fileExistsImageUriPath = pathOK
            val fileOK = File(pathOK)
            if (fileOK.exists()) {
                imageBase64 = fileToBase64(fileOK)
                val base64String = fileToBase64(fileOK)
                CurrentPhoto = base64String
            }
        }
        capturedImageUri = null;

    }
}
