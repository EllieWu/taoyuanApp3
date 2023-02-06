package com.elliewu.taoyuanapp3

import android.app.DatePickerDialog
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
import java.util.*

var RepairListData = listOf(
          Lists(
            "待執行", "1100515001", "早班 08:00~12:00 "
          ),
          Lists(
            "待執行", "1100515002", "中班 13:00~17:00 "
          ),
          Lists(
            "待執行", "1100825001", "晚班 19:00~23:00 "
          ),
//        Lists(
//            "待執行", "1090301003", "晚班 19:00~23:00 "
//        ),
//        Lists(
//            "待執行", "1090301003", "晚班 19:00~23:00 "
//        ),
//        Lists(
//            "待執行", "1090301003", "晚班 19:00~23:00 "
//        ),
)

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
        }
        if (RepairListData.size == 0) {
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
            workList(RepairListData);
        }
    }
    BottomSpace(navController);
}