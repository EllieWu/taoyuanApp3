package com.elliewu.taoyuanapp3

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*
import java.util.Base64.getEncoder
import android.util.Base64
import android.provider.MediaStore
import java.io.FileInputStream
var CurrentPhoto by mutableStateOf("")
@Preview
@Composable
fun CameraTest(navController: NavHostController = rememberNavController()) {
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
                text = AnnotatedString("返回"), style = TextStyle(
                    color = Color.White,
                    fontSize = 20.sp,
                    textAlign = TextAlign.End,
                    textDecoration = TextDecoration.Underline,
                ), onClick = {
                    navController.navigate(Screen.MA3_1.route)
                }, modifier = Modifier.padding(start = 20.dp)
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
        Camera()
    }
}

//相機功能
@Composable
fun Camera() {
    val context = LocalContext.current
    val file = context.createImageFile()
    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(context), BuildConfig.APPLICATION_ID + ".provider", file
    )

    var capturedImageUri by remember { mutableStateOf(Uri.EMPTY) }
    var cameraCapturedImageUri by remember { mutableStateOf(Uri.EMPTY) }

    var imageBase64 by remember { mutableStateOf<String?>(null) }
    var fileExistsImageUriPath  by remember { mutableStateOf<String?>(null) }

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

    Row(
    ) {
        Button(modifier = Modifier.padding(horizontal = 8.dp), onClick = {
            val permissionCheckResult =
                ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
            if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                cameraLauncher.launch(uri)
            } else {
                permissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }) {
            Text(text = "拍照")
        }
        Button(modifier = Modifier.padding(horizontal = 8.dp), onClick = {
            val permissionCheckResult = ContextCompat.checkSelfPermission(
                context, Manifest.permission.READ_MEDIA_IMAGES
            )
            if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                galleryLauncher.launch("image/*")
            } else {
                imagePermissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
            }
        }) {
            Text(text = "相簿")
        }
    }

//    if(cameraCapturedImageUri != null){
//        //capturedImageUri = cameraCapturedImageUri
//        cameraCapturedImageUri = capturedImageUri
//    }

    if (capturedImageUri?.path != null) {
        if (capturedImageUri.path?.isNotEmpty() == true) {
            Image(
                modifier = Modifier
                    .padding(16.dp, 8.dp)
                    .background(Color.Yellow),
                painter = rememberImagePainter(capturedImageUri),
                contentDescription = null
            )

            Row(
            ) {
                Button(modifier = Modifier.padding(horizontal = 8.dp), onClick = {

                    val base64StringOK = imageBase64
                    val pathOK = fileExistsImageUriPath
                    if (pathOK != null) {
                        val fileOK = File(pathOK)
                        if (fileOK.exists()) {
                            val base64String = fileToBase64(fileOK)
                            CurrentPhoto = base64String
                        }
                    }

                }) {
                    Text(text = "Send")
                }
            }

            imageBase64 = null
            fileExistsImageUriPath = null
            val contextOK: Context = LocalContext.current
            val uriOK: Uri = capturedImageUri
            val pathOK = getPathFromUri(contextOK, uriOK)
            if (pathOK != null) {
                fileExistsImageUriPath = pathOK
                val fileOK = File(pathOK)
                if (fileOK.exists()) {
                    imageBase64 = fileToBase64(fileOK)
                }
            }

            if (cameraCapturedImageUri != null) {
                val inputStream =
                    LocalContext.current.contentResolver.openInputStream(cameraCapturedImageUri)
                val MinWenBitmap = BitmapFactory.decodeStream(inputStream)
                val bitmap: Bitmap = MinWenBitmap //BitmapFactory.decodeStream(inputStream)
                val imageFileName =
                    "JPEG_" + SimpleDateFormat("yyyyMMdd_HHmmss").format(Date()) + "_myImage.jpg"
                val dir =
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                val file = File(dir, imageFileName)
                val fos = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
                fos.flush()
                fos.close()

                cameraCapturedImageUri = null
                Log.i("MyTag", "This is an informational message.")
            }
        }
    }
    if (cameraCapturedImageUri?.path != null) {
        if (cameraCapturedImageUri.path?.isNotEmpty() == true) {
            Image(
                modifier = Modifier
                    .padding(16.dp, 8.dp)
                    .background(Color.Yellow),
                painter = rememberImagePainter(cameraCapturedImageUri),
                contentDescription = null
            )

            Row(
            ) {
                Button(modifier = Modifier.padding(horizontal = 8.dp), onClick = {

                    val base64StringOK = imageBase64
                    val pathOK = fileExistsImageUriPath
                    if (pathOK != null) {
                        val fileOK = File(pathOK)
                        if (fileOK.exists()) {
                            val base64String = fileToBase64(fileOK)
                            CurrentPhoto = base64String
                        }
                    }

                }) {
                    Text(text = "Send")
                }
            }

            imageBase64 = null
            fileExistsImageUriPath = null
            val contextOK: Context = LocalContext.current
            val uriOK: Uri = capturedImageUri
            val pathOK = getPathFromUri(contextOK, uriOK)
            if (pathOK != null) {
                fileExistsImageUriPath = pathOK
                val fileOK = File(pathOK)
                if (fileOK.exists()) {
                    imageBase64 = fileToBase64(fileOK)
                    if(imageBase64 == null)
                        CurrentPhoto = ""
                    else
                        CurrentPhoto = imageBase64.toString()
                }
            }
        }
    }
}

fun getPathFromUri(context: Context, uri: Uri): String? {
    val projection = arrayOf(MediaStore.Images.Media.DISPLAY_NAME)
    val cursor = context.contentResolver.query(uri, projection, null, null, null)
    if (cursor != null) {
        val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
        cursor.moveToFirst()
        val name = cursor.getString(columnIndex)
        val file = File(context.cacheDir, name)
        val inputStream = context.contentResolver.openInputStream(uri)
        val outputStream = FileOutputStream(file)
        inputStream?.copyTo(outputStream)
        inputStream?.close()
        outputStream.close()
        cursor.close()
        return file.absolutePath
    }
    return null
}

fun fileToBase64(file: File): String {
    val inputStream = FileInputStream(file)
    val bytes = ByteArray(file.length().toInt())
    inputStream.read(bytes)
    inputStream.close()
    return Base64.encodeToString(bytes, Base64.DEFAULT)
}