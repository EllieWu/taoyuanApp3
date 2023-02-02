package com.elliewu.taoyuanapp3

import android.app.DatePickerDialog
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
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.*

var msggg by mutableStateOf( FakeData.workListData)

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
@Preview(showBackground = true)
@Composable
fun MA3_1(
    //navController: NavController
    onClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color(232, 232, 232))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .size(width = 150.dp, height = 50.dp)
                .background(Color(62, 83, 140))
        ) {
            Text(
                modifier = Modifier.size(width = 250.dp, height = 30.dp),
                text = "外巡巡檢工單",
                fontSize = 24.sp,
                textAlign = TextAlign.End,
                color = Color(255, 255, 255),
            )
            //var enabled by remember { mutableStateOf(true)}
            ClickableText(
                text = AnnotatedString("登出"),
                style = TextStyle(
                    color = White,
                    fontSize = 20.sp,
                    textAlign = TextAlign.End
                ),
                onClick = {
                    //navController.navigate(Screen.login.route)
                    onClick()
                })
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
                        onClick();
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
                            color = Color(255, 0, 0)
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
            workList(msggg);
        }
    }
}



@Composable
fun listCard(list: Lists){
    Card(modifier = Modifier.padding(bottom = 20.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .size(width = 0.dp, height = 100.dp)
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

@Composable
fun workList(messages: List<Lists>){
    LazyColumn(){
        items(messages){message ->
            listCard(message)
        }
    }
}

//@Preview(showBackground = true)
@Composable
fun Preview() {
    MA3_1();
    //workList(msggg);

}