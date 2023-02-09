package com.elliewu.taoyuanapp3

import android.annotation.SuppressLint
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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
//var MA3_3_msggg by mutableStateOf(FakeData.workListData)
var MA3_3_msggg by mutableStateOf(ReportFakeData.ReportListData)
var MA3_3_date by mutableStateOf(SimpleDateFormat("yyyy-MM-dd").format(Date()));

data class ReportLists(val ReportCode: String, val State: String, val ReportTime: String,val ReportTitle:String)
object ReportFakeData {
    var ReportListData = listOf(
        ReportLists(
            "111032201", "等待派工", "2022/3/22 7:37:46 AM","VP-6人口蓋異常"
        ),
        ReportLists(
            "111032202", "測試中", "2022/3/22 13:42:07 AM","欄杆損壞"
        )
    )
}
var MA3_3_1_ReportCode by mutableStateOf("")
@Preview(device = Devices.PIXEL_C)
@Preview(device = Devices.PIXEL_3A)
@Preview(showBackground = true)
@Composable
fun MA3_3(
    navController: NavHostController = rememberNavController()
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(226,230,239)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(

            modifier = Modifier
                .fillMaxWidth()
                .size(width = 250.dp, 50.dp)
                .background(Color(65,96,176)),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()

                    .weight(1f)
                    .padding(start = 100.dp),
                //size(width = 250.dp, height = 30.dp),
                text = "報修",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = Color(255, 255, 255),
            )
            ClickableText(
                text = AnnotatedString("新增報修"),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 20.sp,
                    textAlign = TextAlign.End,
                ),
                onClick = {
                    //navController.navigate(Screen.login.route)
                },
                modifier = Modifier.padding(end = 20.dp)
            )
            //var enabled by remember { mutableStateOf(true)}

        }
        Column(modifier = Modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp)) {
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
                    fontSize = 18.sp,
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
                    contentPadding = PaddingValues(0.dp),
                    shape = RoundedCornerShape(50),
                    modifier = Modifier.size(width = 60.dp, height = 30.dp),
                    onClick = {
                        MA3_2_date = SimpleDateFormat("yyyy-MM-dd").format(Date())
                    },
                )
                {
                    ClickableText(
                        text = AnnotatedString("今日"),
                        style = TextStyle(
                            color = Color(202, 140, 62),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                        ),
                        onClick = {
                            //當天日期
                            MA3_3_date = SimpleDateFormat("yyyy-MM-dd").format(Date())
                        })
                }

            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 15.dp)
            ) {
                Image(
                    painterResource(id = R.drawable.calendar),
                    contentDescription = "null",
                    modifier = Modifier
                        .size(38.dp)
                        .padding(end = 10.dp)
                )

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
                        val pickdatecalendar = Calendar.getInstance()
                        pickdatecalendar.set(year,month,dayOfMonth)
                        MA3_3_date = SimpleDateFormat("yyyy-MM-dd").format(pickdatecalendar.time)
                    }, year, month, day
                )

                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { datePickerDialog.show() }) {
                    Text(
                        text = MA3_3_date,
                        fontSize = 18.sp,
                        color = Color(163,76,60)
                    )
                    MA3_3_MakeListCom(MA3_3_date,Login_UserId);
                }
            }
        }
        if (MA3_3_msggg.size == 0) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(Color(231,238,248))
            ) {
                Text(
                    modifier = Modifier.padding(top = 180.dp),
                    text = "暫無報修工單",
                    color = Color(131,132,134),
                    fontWeight = FontWeight.Bold,
                )
            }
        } else {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(Color(231,238,248))
            ) {
                ReportList(MA3_3_msggg,navController);
            }
        }
    }
    BottomSpace(navController);
}

@Composable
fun ReportCard(list: ReportLists,navController :NavHostController = rememberNavController()) {
    Button(
        modifier = Modifier
            .padding(start = 5.dp, end = 5.dp, bottom = 5.dp)
            .fillMaxSize(),
        border = BorderStroke(0.dp, Color.Transparent),
        elevation = null,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        onClick = {
            Log.d("ButtonEvent", Screen.MA3_3_1.route)
            //val screen = Screen.MA3_1_1.route
            MA3_3_1_ReportCode = list.ReportCode
            navController.navigate(Screen.MA3_3_test.route)
        })
    {
        Card(modifier = Modifier.fillMaxSize()) {
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .size(100.dp,100.dp)
                    .fillMaxWidth()
                    //.size(width = 0.dp, height = 100.dp)
                    .background(Color(255, 255, 255))
            ) {
                val stateColor = if(list.State == "已完工"){
                    Color(128,128,128)
                }else{
                    Color(65, 89, 151)
                }
                Box(
                    contentAlignment = Alignment.Center, modifier = Modifier
                        .size(width = 70.dp, height = 30.dp)
                        .background(stateColor)
                ) {
                    Text(
                        text = list.State,
//                        Modifier.padding(16.dp),
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontSize = 12.sp,
                    )
                }

            }
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 20.dp,start = 20.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically){
                    Text(
                        modifier = Modifier.padding(bottom = 10.dp),
                        text = "報修時間:",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(105, 105, 105)
                    )
                    Text(
                        modifier = Modifier.padding(bottom = 10.dp),
                        text = list.ReportTime,
                        fontSize = 16.sp,
                        color = Color(105, 105, 105)
                    )
                }
                Text(
                    text = list.ReportTitle,
                    fontSize = 18.sp,
                    color = Color(163,76,60),
                    fontWeight = FontWeight.Bold
                )

            }
        }

    }
}


@Composable
fun ReportList(messages: List<ReportLists>,navController :NavHostController = rememberNavController()) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(messages) { message ->
            ReportCard(message,navController)
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MA3_3_MakeListCom(Date:String,UserID:String){
    MA3_3_MakeList(Date,UserID)
}
fun MA3_3_MakeList(Date:String, UserID:String){
    GlobalScope.launch(Dispatchers.IO) {
        var MA3_RequestJsonObject = JSONObject();
        MA3_RequestJsonObject.put("Function", "ReportList")
        MA3_RequestJsonObject.put("Date", Date)
        MA3_RequestJsonObject.put("UserID", UserID)
        val responseString = HttpRequestTest(MA3_RequestJsonObject)
        Log.d("MA3_3",responseString)
        if(responseString!="Error"){
            var gson = Gson();
            var TestWorkList:ReportList_Response = gson.fromJson(responseString,ReportList_Response::class.java)
            var ReportListDatas = listOf(
                ReportLists(
                    "", "", "",""
                )
            )
            ReportListDatas = ReportListDatas - ReportListDatas[ReportListDatas.size -1]
            if(TestWorkList.ReportList != null){
                TestWorkList.ReportList!!.forEach {
                    var ReportListd = ReportLists(it.ReportCode.toString(),it.State.toString(),it.ReportTime.toString(),it.ReportTitle.toString())
                    ReportListDatas += ReportListd
                }
            }
            MA3_3_msggg = ReportListDatas
        }
    }
}