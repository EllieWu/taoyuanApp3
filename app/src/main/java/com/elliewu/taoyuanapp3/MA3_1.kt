package com.elliewu.taoyuanapp3

import android.app.DatePickerDialog
import android.graphics.Color.red
import android.os.Bundle
import android.widget.DatePicker
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.elliewu.taoyuanapp3.ui.theme.TaoyuanApp3Theme
import java.util.*


data class List(val state: String,val workID:String,val time: String)
object FakeData{
    var workList = listOf(
        List(
            "待執行","1090301001","早班 08:00~12:00 "
        ),
        List(
            "待執行","1090301002","中班 13:00~17:00 "
        ),
        List(
            "待執行","1090301003","晚班 19:00~23:00 "
        ),
    )
}

//@Composable
//fun listCard(list: List){
//    Text(text = list.state),
//}

//3-1巡檢工單
@Composable
fun MA3_1(
    //navController: NavController
    onClick: () -> Unit = {}
) {
        Column(modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color(232,232,232))) {
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
                        .padding(bottom = 10.dp)){
                    Text(
                        text = "選擇日期",
                        fontWeight = FontWeight.Bold,
                        color = Color(105,105,105)
                    )
                    Button(
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(red = 255, green = 229, blue = 178)),
                        border = BorderStroke(1.dp, Color(202,140,62)),
                        shape = RoundedCornerShape(50),
                        modifier = Modifier.size(width = 70.dp, height = 40.dp),
                        onClick = {
                            onClick();
                        },)
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
                                color = Color(202,140,62),
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            onClick = {
                                //當天日期

                            })
                    }

                }
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 30.dp)) {
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
                        if(datePicked == ""){
                            Text(
                                text = "選擇日期",
                                fontWeight = FontWeight.Bold,
                                color = Color(105,105,105))
                        }else{
                            Text(
                                text = "${datePicked}",
                                color = Color(255,0,0)
                            )
                        }
                    }
                }
                Text(modifier = Modifier.padding(bottom = 20.dp),
                    text = "選擇巡檢工單",
                    color = Color(105,105,105),
                    fontWeight = FontWeight.Bold)

                Row(verticalAlignment = Alignment.CenterVertically,modifier = Modifier.fillMaxWidth().size(width = 0.dp,height = 100.dp).background(Color(255,255,255)).border(1.dp, Color.Black)){
                   Text("待執行")
                    Column() {
                        Text(text = "1090301001")
                        Text(text = "早班 08:00~12:00")
                    }
                }

            }
        }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    MA3_1();
}