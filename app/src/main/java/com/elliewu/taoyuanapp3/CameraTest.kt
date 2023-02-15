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
import coil.compose.ImagePainter.State.Empty.painter
import coil.compose.rememberImagePainter
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*
import java.util.Base64.getEncoder
import android.util.Base64


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

    if(cameraCapturedImageUri != null){
        capturedImageUri = cameraCapturedImageUri
    }

    if (capturedImageUri?.path != null) {
        if (capturedImageUri.path?.isNotEmpty() == true) {
            Image(
                modifier = Modifier
                    .padding(16.dp, 8.dp)
                    .background(Color.Yellow),
                painter = rememberImagePainter(capturedImageUri),
                contentDescription = null
            )

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

//        val byteArray = byteArrayOf(0x00, 0x01, 0x02, 0x03)
//        val base64Strina = Base64.encodeToString(byteArray, Base64.DEFAULT)
//        val base64String = bitmapToBase64(MinWenBitmap)

                Log.i("MyTag", "This is an informational message.")
            }
        }
    }
}


@Composable
fun bitmapToBase64(bitmap: Bitmap): String {
    val outputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream)
    val byteArray = outputStream.toByteArray()
    return Base64.encodeToString(byteArray, Base64.DEFAULT)
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