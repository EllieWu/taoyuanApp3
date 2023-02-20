package com.elliewu.taoyuanapp3

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
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
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
var FinishState by mutableStateOf("待執行")
data class MA3_1_1_btn2_Info(var State: String, var FinishContent: String,var FinishPhoto : String)
var MA3_1_1_btn2_Info_msggg by mutableStateOf(MA3_1_1_btn2_Info("","",""))

@Preview(device = Devices.PIXEL_C)
@Preview(device = Devices.PIXEL_3A)
@Composable
fun MA3_1_1_Bottombtn2(WorkCode:String?="",WorkTime:String?="",navController: NavHostController = rememberNavController()) {
    val coroutineScope = rememberCoroutineScope()
    loadingDialog()
    var repairRemark by remember { mutableStateOf(MA3_1_1_btn2_Info_msggg.FinishContent) }
    repairRemark = MA3_1_1_btn2_Info_msggg.FinishContent
    MA3_1_1_Info_MakeListCom(WorkCode.toString());
    MA3_1_1_btn2_MakeListCom(WorkCode.toString());
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(231, 232, 231)),
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
//                    var fullMA3_2_1_path =
//                        Screen.MA3_2_1.route + "?RepairCode=${MA3_2_1_msggg.RepairCode}&State=${MA3_2_1_msggg.State}"
//                    navController.navigate(fullMA3_2_1_path)
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

                text = "巡檢完工填報",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = Color(255, 255, 255),
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp, bottom = 0.dp, start = 20.dp, end = 20.dp)
                .verticalScroll(rememberScrollState())
        ) {

            //DropdownDemo()
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            {
                Text(
                    text = "巡檢日期",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,

                    color = Color(128, 127, 129),
                    //modifier = Modifier.padding( bottom = 10.dp),
                    //.fillMaxWidth(),
                    textAlign = TextAlign.Start,
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 65.dp), horizontalArrangement = Arrangement.Start
                )
                {
                    Text(
                        text = MA3_1_1_info_msggg.Date,
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
                    text = "工單編號",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,

                    color = Color(128, 127, 129),
                    //modifier = Modifier.padding( bottom = 10.dp),
                    //.fillMaxWidth(),
                    textAlign = TextAlign.Start,
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 65.dp), horizontalArrangement = Arrangement.Start
                )
                {
                    Text(
                        text = MA3_1_1_info_msggg.WorkCode,
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
                    text = "填報人",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,

                    color = Color(128, 127, 129),
                    // modifier = Modifier.padding( bottom = 10.dp),
                    //.fillMaxWidth(),
                    textAlign = TextAlign.Start,
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 82.dp), horizontalArrangement = Arrangement.Start
                )
                {
                    Text(
                        text = MA3_1_1_info_msggg.UserName,
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
                    text = "完成狀態",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(128, 127, 129),
                    // modifier = Modifier.padding( bottom = 10.dp),
                    //.fillMaxWidth(),
                    textAlign = TextAlign.Start,
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp), horizontalArrangement = Arrangement.Start
                )
                {
                    when (MA3_1_1_btn2_Info_msggg.State) {
                        "待執行" -> DropdownDemo(0)
                        "執行中" -> DropdownDemo(1)
                        "已完工" -> DropdownDemo(2)
                        else -> DropdownDemo(0)
                    }

                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            {
                TextField(
                    modifier = Modifier
                        .size(2000.dp, 180.dp)
                        .fillMaxSize(),
                    value = repairRemark,
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(103, 103, 103),
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White,
                        cursorColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    onValueChange = {
                        repairRemark = it
                        MA3_1_1_btn2_Info_msggg.FinishContent = it
                    },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.None,
                        autoCorrect = true,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                )
            }
            CameraTest_Jeremy(LocalContext.current)
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

                var imageBytes = Base64.decode("", 0)
                if(CurrentPhoto != ""){
                    imageBytes = Base64.decode(CurrentPhoto, 0)
                }
                else
                {
                    imageBytes = Base64.decode(MA3_1_1_btn2_Info_msggg.FinishPhoto, 0)
                }

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
                        //Log.d("MA3_1_1_btn2_submit","Onclick")
                        showDialog = true
                        GlobalScope.launch(Dispatchers.IO) {
                            var RequestJsonObject = JSONObject();
                            RequestJsonObject.put("Function", "FormUploadWork")
                            RequestJsonObject.put("UserID", Login_UserId)
                            RequestJsonObject.put("WorkCode", WorkCode)
                            RequestJsonObject.put("State", FinishState)
                            RequestJsonObject.put("FinishContent", repairRemark)
                            if(CurrentPhoto != "")
                                RequestJsonObject.put("FinishPhoto", CurrentPhoto)
                            else
                                RequestJsonObject.put("FinishPhoto", MA3_1_1_btn2_Info_msggg.FinishPhoto)
                            val responseString = HttpRequestTest(RequestJsonObject)
                            //Log.d("MA3_1_1_Buttombtn2",responseString)
                            if(responseString!="Error"){
                                var gson = Gson();
                                var Response:LocateFormUpload_Response = gson.fromJson(responseString,LocateFormUpload_Response::class.java)
                                if(Response.Feedback == "TRUE"){
                                    //TODO:跳轉回首頁
                                    GlobalScope.launch(Dispatchers.Main) {
                                        navController.navigate(Screen.MA3_1.route)
                                    }
                                }
                            }
                            showDialog = false
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
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MA3_1_1_btn2_MakeListCom(WorkCode:String){
    MA3_1_1_btn2_MakeList(WorkCode)
}
fun MA3_1_1_btn2_MakeList(WorkCode:String){
    GlobalScope.launch(Dispatchers.IO) {
        showDialog = true
        var RequestJsonObject = JSONObject();
        RequestJsonObject.put("Function", "FormRequestWork")
        RequestJsonObject.put("WorkCode", WorkCode)
        val responseString = HttpRequestTest(RequestJsonObject)
        //Log.d("MA3_1_1_btn2_MakeList",responseString)
        if(responseString!="Error"){
            var gson = Gson();
            var WorkInfoResponse:FormRequestWork_Response = gson.fromJson(responseString,FormRequestWork_Response::class.java)
            var workListDatas = MA3_1_1_btn2_Info("","","");
            try {
                workListDatas.State = WorkInfoResponse.State.toString();
                workListDatas.FinishContent = WorkInfoResponse.FinishContent.toString();
                workListDatas.FinishPhoto = WorkInfoResponse.FinishPhoto.toString();
            }
            catch (e:java.lang.Exception){

            }
            MA3_1_1_btn2_Info_msggg = workListDatas
        }
        showDialog = false
    }
}
@Composable
fun JeremyloadingDialog(AAA:Boolean){
    if (AAA) {
        Dialog(
            onDismissRequest = { showDialog = false },
            DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            Box(
                contentAlignment= Alignment.Center,
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.White, shape = RoundedCornerShape(12.dp))
            ) {
                Column {
                    CircularProgressIndicator(modifier = Modifier.padding(8.dp, 0.dp, 0.dp, 0.dp))
                    Text(text = "Loading", Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp))
                }
            }
        }
    }
}