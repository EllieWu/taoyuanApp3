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
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.elliewu.taoyuanapp3.RepairFakeData.RepairListData
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

var MA3_2_msggg by mutableStateOf(RepairFakeData.RepairListData)
var MA3_2_date by mutableStateOf(SimpleDateFormat("yyyy-MM-dd").format(Date()));

data class RepairLists(val State: String, val RepairCode: String, val RepairTitle: String)
object RepairFakeData {
    var RepairListData = listOf(
        RepairLists(
            "?????????", "111032201", "????????????"
        ),
        RepairLists(
            "?????????", "111032202", "???????????????????????????"
        )
    )
}


//3-2????????????
@Preview(device = Devices.PIXEL_C)
@Preview(device = Devices.PIXEL_3A)
@Preview(showBackground = true)
@Composable
fun MA3_2(
    navController: NavHostController = rememberNavController()
) {
    CurrentPhoto=""
    loadingDialog();
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
                    .weight(1f),
                //size(width = 250.dp, height = 30.dp),
                text = "????????????",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = Color(255, 255, 255),
            )
            //var enabled by remember { mutableStateOf(true)}

        }
        Column(modifier = Modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 80.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            ) {
                Text(
                    text = "????????????",
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
                       // navController.navigate(Screen.MA3_3.route)
                        MA3_2_date = SimpleDateFormat("yyyy-MM-dd").format(Date())
                    },
                )
                {
//                        Text(
//                            text = "??????",
//                            fontSize = 15.sp,
//                            fontWeight = FontWeight.Bold,
//                            color = Color(202,140,62)
//                        )
                    ClickableText(
                        text = AnnotatedString("??????"),
                        style = TextStyle(
                            color = Color(202, 140, 62),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                        ),
                        onClick = {
                            //????????????
                            MA3_2_date = SimpleDateFormat("yyyy-MM-dd").format(Date())
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
                        MA3_2_date = SimpleDateFormat("yyyy-MM-dd").format(pickdatecalendar.time)
                    }, year, month, day
                )
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { datePickerDialog.show() }) {
                    Text(
                        text = MA3_2_date,
                        fontSize = 18.sp,
                        color = Color(163,76,60)
                    )
                    MA3_2_MakeListCom(MA3_2_date,Login_UserId);
                }
            }
            if (MA3_2_msggg.size == 0) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(Color(238, 239, 241))
                ) {
                    Text(
                        modifier = Modifier.padding(top = 180.dp),
                        text = "??????????????????",
                        color = Color(131,132,134),
                        fontWeight = FontWeight.Bold,
                    )
                }
            } else {
                RepairList(MA3_2_msggg,navController);
            }
        }
    }
    BottomSpace(navController)
}

@Composable
fun RepairCard(list: RepairLists,navController :NavHostController = rememberNavController()) {

    Button(
        modifier = Modifier
            //.padding(start = 5.dp, end = 5.dp, bottom = 5.dp)
            .fillMaxSize()
            .padding(vertical = 10.dp),
        border = BorderStroke(0.dp, Color.Transparent),
        elevation = null,
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        onClick = {
            //Log.d("showDialog", showDialog.toString())
            //Log.d("ButtonEvent", Screen.MA3_2_1.route)
                var fullMA3_2_1_path = Screen.MA3_2_1.route + "?RepairCode=${list.RepairCode}&State=${list.State}"
                navController.navigate(fullMA3_2_1_path)
        })
    {
        Card(modifier = Modifier.fillMaxSize()) {
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .size(100.dp, 100.dp)
                    .fillMaxWidth()
                    //.size(width = 0.dp, height = 100.dp)
                    .background(Color(255, 255, 255))
            ) {
                val stateColor = if(list.State == "?????????"){
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
                        color = White,
                        fontSize = 12.sp,
                    )
                }

            }
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 20.dp, start = 20.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically){
                    Text(
                        modifier = Modifier.padding(bottom = 10.dp),
                        text = "????????????: ${list.RepairCode.toString()}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(105, 105, 105)
                    )
                }
                Text(
                    text = list.RepairTitle,
                    fontSize = 18.sp,
                    color = Color(163,76,60),
                    fontWeight = FontWeight.Bold
                )

            }
        }

    }
}

@Composable
fun RepairList(messages: List<RepairLists>,navController :NavHostController = rememberNavController()) {
    LazyColumn(modifier = Modifier.fillMaxSize(), state = LazyListState()) {
        items(messages) { message ->
            RepairCard(message,navController)
        }
    }
}


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MA3_2_MakeListCom(Date:String,UserID:String){
    MA3_2_MakeList(Date,UserID)
}
fun MA3_2_MakeList(Date:String, UserID:String){
    GlobalScope.launch(Dispatchers.IO){
        var MA3_RequestJsonObject = JSONObject();
        MA3_RequestJsonObject.put("Function", "RepairList")
        MA3_RequestJsonObject.put("Date", Date)
        MA3_RequestJsonObject.put("UserID", UserID)
        showDialog = true;
        val responseString = HttpRequestTest(MA3_RequestJsonObject)
        //Log.d("MA3_2",responseString)
        if(responseString!="Error"){
            var gson = Gson();
            var TestWorkList:RepairList_Response = gson.fromJson(responseString,RepairList_Response::class.java)
            var RepairListDatas = listOf(
                RepairLists(
                    "", "", ""
                )
            )
            RepairListDatas = RepairListDatas - RepairListDatas[RepairListDatas.size -1]
            if(TestWorkList.RepairList != null){
                TestWorkList.RepairList!!.forEach {
                    var RepairListd = RepairLists(it.State.toString(),it.RepairCode.toString(),it.RepairTitle.toString())
                    RepairListDatas += RepairListd
                }
            }
            MA3_2_msggg = RepairListDatas
        }
        showDialog = false;
    }
}

