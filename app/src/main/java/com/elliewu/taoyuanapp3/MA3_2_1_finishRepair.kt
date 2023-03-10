package com.elliewu.taoyuanapp3

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject

data class Finish_RepairInfoList(
    var State: String,
    var RepairContent: String,
    var RepairPhoto:String,
    var Edit:String,
    )
var Finish_RepairListData =
    Finish_RepairInfoList(
        "????????????",
        "?????????",
        "",
        "FALSE",
    )
var MA3_2_1_finishRepair_msggg by mutableStateOf(Finish_RepairInfoList(
    "????????????",
    "?????????",
    "",
    "FALSE",
))
@Preview(device = Devices.PIXEL_C)
@Preview(device = Devices.PIXEL_3A)
@Composable
fun MA3_2_1_finishRepair(navController: NavHostController = rememberNavController()) {
    MA3_2_1_finishRepair_MakeListCom(MA3_2_1_RepairCode)
    var repairRemark by remember { mutableStateOf(MA3_2_1_finishRepair_msggg.RepairContent) }
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
                text = AnnotatedString("??????"),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 20.sp,
                    textAlign = TextAlign.End,
                ),
                onClick = {
                    //navController.navigate(Screen.MA3_2_1.route)
                    var fullMA3_2_1_path =
                        Screen.MA3_2_1.route + "?RepairCode=${MA3_2_1_msggg.RepairCode}&State=${MA3_2_1_msggg.State}"
                    navController.navigate(fullMA3_2_1_path)
                },
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(end = 40.dp),
                //size(width = 250.dp, height = 30.dp),

                text = "??????????????????",
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
                    text = "????????????",
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
                        text = MA3_2_1_msggg.RepairCode,
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
                    text = "????????????",
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
                        .padding(start = 65.dp), horizontalArrangement = Arrangement.Start
                )
                {
                    Text(
                        text = MA3_2_1_msggg.RepairTitle,
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
                    text = "????????????",
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
                    when (MA3_2_1_finishRepair_msggg.State) {
                        "?????????" -> DropdownDemo(0)
                        "?????????" -> DropdownDemo(1)
                        "?????????" -> DropdownDemo(2)
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
                repairRemark = MA3_2_1_finishRepair_msggg.RepairContent
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
                        MA3_2_1_finishRepair_msggg.RepairContent = it
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
                                .size(25.dp).padding(end = 5.dp),
                            painter = painterResource(id = R.drawable.camera),
                            contentDescription = "BackIcon",
                            tint = Color.White
                        )
                        if(CurrentPhoto == "" && MA3_2_1_finishRepair_msggg.RepairPhoto.isNullOrEmpty()){
                            Text(
                                text = "??????",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                            )
                        }
                        else if(!MA3_2_1_finishRepair_msggg.RepairPhoto.isNullOrEmpty()){
                            Text(
                                text = "????????????",
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
                var imageBytes = Base64.decode(CurrentPhoto, 0)
                if(!MA3_2_1_finishRepair_msggg.RepairPhoto.isNullOrEmpty())
                {
                    imageBytes = Base64.decode(MA3_2_1_finishRepair_msggg.RepairPhoto, 0)
                    if(CurrentPhoto != "")
                        imageBytes = Base64.decode(CurrentPhoto, 0)
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
                        GlobalScope.launch(Dispatchers.IO) {
                            showDialog = true;
                            var RequestJsonObject = JSONObject();
                            RequestJsonObject.put("Function", "FormUploadService")
                            RequestJsonObject.put("UserID", Login_UserId)
                            RequestJsonObject.put("RepairCode", MA3_2_1_RepairCode)
                            RequestJsonObject.put("State", FinishState)
                            RequestJsonObject.put("RepairContent", MA3_2_1_finishRepair_msggg.RepairContent)
                            if(CurrentPhoto != "")
                                RequestJsonObject.put("RepairPhoto", CurrentPhoto)
                            else
                                RequestJsonObject.put("RepairPhoto", MA3_2_1_finishRepair_msggg.RepairPhoto)
                            val responseString = HttpRequestTest(RequestJsonObject)
                            //Log.d("MA3_2_1_finishRepair_Upload",responseString)
                            if(responseString!="Error"){
                                var gson = Gson();
                                var WorkInfoResponse:FormUploadService_Response = gson.fromJson(responseString,FormUploadService_Response::class.java)
                                if(WorkInfoResponse.Feedback == "TRUE")
                                {
                                    GlobalScope.launch(Dispatchers.Main){
//                                        var fullMA3_2_1_path = Screen.MA3_2_1.route + "?RepairCode=${MA3_2_1_RepairCode}&State=${MA3_2_1_State}"
//                                        navController.navigate(fullMA3_2_1_path)
                                        navController.navigate(Screen.MA3_2.route)
                                        CurrentPhoto = ""
                                    }
                                    MA3_2_1_finishRepair_msggg.State = FinishState
                                    MA3_2_1_finishRepair_MakeList(MA3_2_1_RepairCode);
                                }
                            }
                            showDialog = false;
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


@Composable
fun DropdownDemo(State: Int) {
    var expanded by remember { mutableStateOf(false) }
    val items = listOf("?????????","?????????", "?????????")
    var selectedIndex by remember { mutableStateOf(State) }
    Box(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)) {
        Text(
            items[selectedIndex],
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 36.dp)
                .background(Color.White)
                .padding(vertical = 5.dp)
                .clickable(
                    onClick = { expanded = true
                        //FinishState = items[selectedIndex]
                    }
                ),
            color = Color(103, 103, 103),
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .size(260.dp, 160.dp)
                .fillMaxWidth()
                .background(
                    Color.White
                )
        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    selectedIndex = index
                    expanded = false
                }) {
                    Text(text = s)
                }
            }
            FinishState = items[selectedIndex]
        }
    }
}
//TODO:Jeremy??????
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MA3_2_1_finishRepair_MakeListCom(RepairCode:String){
    MA3_2_1_finishRepair_MakeList(RepairCode)
}
fun MA3_2_1_finishRepair_MakeList(RepairCode:String){
    GlobalScope.launch(Dispatchers.IO) {
        showDialog = true;
        var RequestJsonObject = JSONObject();
        RequestJsonObject.put("Function", "FormRequestService")
        RequestJsonObject.put("RepairCode", RepairCode)
        val responseString = HttpRequestTest(RequestJsonObject)
        //Log.d("MA3_2_1_finishRepair",responseString)
        if(responseString!="Error"){
            var gson = Gson();
            var WorkInfoResponse:FormRequestService_Response = gson.fromJson(responseString,FormRequestService_Response::class.java)
            var workListDatas = Finish_RepairListData
            workListDatas.State = WorkInfoResponse.State.toString()
            workListDatas.RepairContent = WorkInfoResponse.RepairContent.toString()
            workListDatas.RepairPhoto = WorkInfoResponse.RepairPhoto.toString()
            workListDatas.Edit = WorkInfoResponse.Edit.toString()
            MA3_2_1_finishRepair_msggg = workListDatas
        }
        showDialog = false;
    }
}