package com.elliewu.taoyuanapp3

import android.app.DatePickerDialog
import android.util.Log
import android.widget.DatePicker
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import com.google.gson.JsonArray
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.util.*

var msggg by mutableStateOf(FakeData.workListData)
data class Lists(val state: String, val workID: String, val time: String)
object FakeData {
    var workListData = listOf(
        Lists(
            "待執行", "1090301001", "早班 08:00~12:00 "
        ),
        Lists(
            "待執行", "1090301002", "中班 13:00~17:00 "
        ),
        Lists(
            "待執行", "1090301003", "晚班 19:00~23:00 "
        ),
        Lists(
            "待執行", "1090301003", "晚班 19:00~23:00 "
        ),
        Lists(
            "待執行", "1090301003", "晚班 19:00~23:00 "
        ),
        Lists(
            "待執行", "1090301003", "晚班 19:00~23:00 "
        ),
    )
}
//var list = FakeData;
//@Composable
//fun listCard(list: List){
//    Text(text = list.state),
//}

//3-1巡檢工單
@Preview(device = Devices.PIXEL_C)
@Preview(device = Devices.PIXEL_3A)
@Preview(showBackground = true)
@Composable
fun MA3_1(
    navController: NavHostController = rememberNavController()
) {
    //TODO:Jeremy增加根據request塞入變數 下方的Date跟UserID會根據外部變化
    MA3_1_MakeListCom("2023-02-06","F123332212");
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

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(start = 60.dp),
                //size(width = 250.dp, height = 30.dp),

                text = "外巡巡檢工單",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = Color(255, 255, 255),
            )
            ClickableText(
                text = AnnotatedString("登出"),
                style = TextStyle(
                    color = White,
                    fontSize = 20.sp,
                    textAlign = TextAlign.End,
                    textDecoration = TextDecoration.Underline,
                    ),
                onClick = {
                    navController.navigate(Screen.login.route)
                },
                modifier = Modifier.padding(end = 20.dp)
            )
            //var enabled by remember { mutableStateOf(true)}

        }
        Column(modifier = Modifier.padding(top = 20.dp, start = 40.dp, end = 40.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            ) {
                Text(
                    text = "選擇日期",
                    fontWeight = FontWeight.Bold,
                    color = Color(105, 105, 105)
                )
                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(
                            red = 255,
                            green = 229,
                            blue = 178
                        )
                    ),
                    border = BorderStroke(1.dp, Color(202, 140, 62)),
                    shape = RoundedCornerShape(50),
                    modifier = Modifier.size(width = 70.dp, height = 40.dp),
                    onClick = {
                        navController.navigate(Screen.login.route)
                    },
                )
                {
//                        Text(
//                            text = "今日",
//                            fontSize = 15.sp,
//                            fontWeight = FontWeight.Bold,
//                            color = Color(202,140,62)
//                        )
                    ClickableText(
                        text = AnnotatedString("今日"),
                        style = TextStyle(
                            color = Color(202, 140, 62),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        onClick = {
                            //當天日期

                        })
                }

            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 30.dp)
            ) {
                Image(
                    painterResource(id = R.drawable.calendar),
                    contentDescription = "null",
                    modifier = Modifier
                        .size(38.dp)
                        .padding(end = 10.dp)
                )
                var datePicked by remember { mutableStateOf("") }

                val context = LocalContext.current
                val year: Int
                val month: Int
                val day: Int

                val calendar = Calendar.getInstance()
                year = calendar.get(Calendar.YEAR)
                month = calendar.get(Calendar.MONTH)
                day = calendar.get(Calendar.DAY_OF_MONTH)
                calendar.time = Date()


                val datePickerDialog = DatePickerDialog(
                    context,
                    { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                        datePicked = "$year-$month-$dayOfMonth"
                    }, year, month, day
                )

                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { datePickerDialog.show() }) {
                    if (datePicked == "") {
                        Text(
                            text = "選擇日期",
                            fontWeight = FontWeight.Bold,
                            color = Color(105, 105, 105)
                        )
                    } else {
                        Text(
                            text = "${datePicked}",
                            color = Color(200, 71, 52)
                        )
                    }
                }
            }
            Text(
                modifier = Modifier.padding(bottom = 20.dp),
                text = "選擇巡檢工單",
                color = Color(105, 105, 105),
                fontWeight = FontWeight.Bold
            )
        }
        if (msggg.size == 0) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(Color(238, 239, 241))
            ) {
                Text(modifier = Modifier.padding(top = 180.dp), text = "暫無巡檢工單")
            }
        } else {
//            Button(onClick = { msggg = msggg - msggg[msggg.size -1] }) {
//
//            }
            workList(msggg,navController);
        }
    }
    BottomSpace(navController)
}
//TODO:Jeremy增加
@Composable
fun MA3_1_MakeListCom(Date:String,UserID:String){
    val coroutineScope = rememberCoroutineScope()
    MA3_1_MakeList(coroutineScope,Date,UserID)
}
fun MA3_1_MakeList(coroutineScope:CoroutineScope,Date:String,UserID:String){
    coroutineScope.launch {
        var MA3_RequestJsonObject = JSONObject();
        MA3_RequestJsonObject.put("Function", "SelectWorkList")
        MA3_RequestJsonObject.put("Date", Date)
        MA3_RequestJsonObject.put("UserID", UserID)
        val responseString = HttpRequestTest(MA3_RequestJsonObject)
        Log.d("MA3_1",responseString)
        if(responseString!="Error"){
            var gson = Gson();
            var TestWorkList:SelectWorkList_Response = gson.fromJson(responseString,SelectWorkList_Response::class.java)
            var workListDatas = listOf(
                Lists(
                    "", "", ""
                )
            )
            workListDatas = workListDatas - workListDatas[workListDatas.size -1]
            if(TestWorkList.WorkList != null){
                TestWorkList.WorkList!!.forEach {
                    var worklistd = Lists(it.State.toString(),it.WorkCode.toString(),it.WorkTime.toString())
                    workListDatas += worklistd
                }
            }
            msggg = workListDatas
        }
    }
}
@Composable
fun listCard(list: Lists,navController :NavHostController = rememberNavController()) {
    //Column(modifier = Modifier.padding(start = 40.dp, end = 40.dp)) {
    Button(
        modifier = Modifier.padding(start = 40.dp, end = 40.dp, bottom = 5.dp).fillMaxSize(),
        border = BorderStroke(0.dp, Color.Transparent),
        elevation = null,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        onClick = {
            Log.d("ButtonEvent", Screen.MA3_1_1.route)
            navController.navigate(Screen.MA3_1_1.route)
        })
    {
        Card(modifier = Modifier.fillMaxSize()) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    //.size(width = 0.dp, height = 100.dp)
                    .background(Color(255, 255, 255))
            ) {
                Box(
                    contentAlignment = Alignment.Center, modifier = Modifier
                        .size(width = 100.dp, height = 100.dp)
                        .background(Color(65, 89, 151))
                ) {
                    Text(
                        text = list.state,
                        Modifier.padding(16.dp),
                        textAlign = TextAlign.Center,
                        color = White
                    )
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .padding(start = 20.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(bottom = 10.dp),
                        text = list.workID,
                        color = Color(105, 105, 105)
                    )
                    Text(
                        text = list.time,
                        color = Color(200, 71, 52),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
//}


@Composable
fun workList(messages: List<Lists>,navController :NavHostController = rememberNavController()) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(messages) { message ->
            listCard(message,navController)
        }
    }
}

//@Preview(showBackground = true)
@Composable
fun Preview() {
    MA3_1();
    //workList(msggg);

}