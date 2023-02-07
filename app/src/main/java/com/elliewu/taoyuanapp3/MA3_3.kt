package com.elliewu.taoyuanapp3

import android.app.DatePickerDialog
import android.util.Log
import android.widget.DatePicker
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
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
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
var MA3_3_msggg by mutableStateOf(FakeData.workListData)
var MA3_3_date by mutableStateOf(SimpleDateFormat("yyyy-MM-dd").format(Date()));

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
                    textDecoration = TextDecoration.Underline,
                ),
                onClick = {
                    //navController.navigate(Screen.login.route)
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

                    },
                )
                {
                    ClickableText(
                        text = AnnotatedString("今日"),
                        style = TextStyle(
                            color = Color(202, 140, 62),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
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
                        color = Color(200, 71, 52)
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
                    .background(Color(238, 239, 241))
            ) {
                Text(modifier = Modifier.padding(top = 180.dp), text = "暫無報修工單")
            }
        } else {
            workList(MA3_3_msggg);
        }
    }
    BottomSpace(navController);
}
@Composable
fun MA3_3_MakeListCom(Date:String,UserID:String){
    val coroutineScope = rememberCoroutineScope()
    MA3_3_MakeList(coroutineScope,Date,UserID)
}
fun MA3_3_MakeList(coroutineScope: CoroutineScope, Date:String, UserID:String){
    coroutineScope.launch {
        var MA3_RequestJsonObject = JSONObject();
        MA3_RequestJsonObject.put("Function", "ReportList")
        MA3_RequestJsonObject.put("Date", Date)
        MA3_RequestJsonObject.put("UserID", UserID)
        val responseString = HttpRequestTest(MA3_RequestJsonObject)
        Log.d("MA3_3",responseString)
        if(responseString!="Error"){
            var gson = Gson();
            var TestWorkList:ReportList_Response = gson.fromJson(responseString,ReportList_Response::class.java)
            var workListDatas = listOf(
                Lists(
                    "", "", ""
                )
            )
            workListDatas = workListDatas - workListDatas[workListDatas.size -1]
            if(TestWorkList.ReportList != null){
                TestWorkList.ReportList!!.forEach {
                    var worklistd = Lists(it.State.toString(),it.ReportCode.toString(),it.ReportTime.toString())
                    workListDatas += worklistd
                }
            }
            MA3_3_msggg = workListDatas
        }
    }
}