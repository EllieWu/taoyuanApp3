package com.elliewu.taoyuanapp3

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
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

data class RepairInfoList(
    val RepairCode: String,
    val State: String,
    val Manager: String,
    val Longitude:String,
    val Latitude:String,
    val RepairTitle:String,
    val RepairContent:String,
    var RepairPhoto:String,

    )
var RepairListData =
    RepairInfoList(
    "111032504",
    "測試中",
    "陳慶明",
    "121.798385",
    "25.140054",
    "階梯有幾處破損",
    "教育中心外側階梯破損嚴重",
    "/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAMCAgMCAgMDAwMEAwMEBQgFBQQEBQoHBwYIDAoMDAsKCwsNDhIQDQ4RDgsLEBYQERMUFRUVDA8XGBYUGBIUFRT/2wBDAQMEBAUEBQkFBQkUDQsNFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBT/wAARCAAZABoDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDyz9in9j9f2idS1PxD4mu59I8AaEc3l1FhXunA3NEjEHaAoyzYOARjk5H3l4W8L/ss3GiWVvoPg3QdW0m4XbHfHTjIrjOCWklG49OvP1qh/wAE92sp/wBiXy7IK1wG1JblU5bzctjOO+0p+GK+S9U8YanpPi7TtOhe7SwwFupEYqkTSZEQPuxXt0465r3spyP+1KMqkaqg4uK1V97+a2/I+YznH4nB8kcNa7u3dX0XTdbne/tv/wDBP7RPCHhC9+I/wvhkt9Ns18/U9DDmREizzNATyAucshJwMkYxivzy4/u/pX7Nfs+eIJ/E37Gvie78SXLXdukOt20rTnOIY3mQD8FAFfjMygsSDxnivnsZRWHrSpp3s2vuPoKVT2tONS1rq59K/sW/tiXn7L/iW7tNStptW8F6s6G+soSPNgkHAniDEDdg4K5G4AcjAr9AP+Gmv2W/F2k6zc3XiTSY4dbkjm1C3vLeeOWR0VFQ425UqI0xt7jPXJr8aV/oab/e/wA96541ZQ2NWk9z7w/a2/be8Kaj4Dv/AIX/AAZtXtPDupTTTavqzRtGJzLKZZUhDHdh3LbmYDIJAGDmvhHIpn8P/AadUSk5O7Ef/9k="
)
var MA3_2_1_msggg by mutableStateOf(RepairListData)

var RepairCode_rem: String? = null
var State_rem: String? = null

@Preview(device = Devices.PIXEL_C)
@Preview(device = Devices.PIXEL_3A)
@Preview(showBackground = true)
@Composable
fun MA3_2_1(RepairCode: String? = "",State: String?="" ,navController : NavHostController = rememberNavController()){
    MA3_2_1_MakeListCom(RepairCode,State)
    RepairCode_rem = RepairCode
    State_rem = State
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
                    navController.navigate(Screen.MA3_2.route)
                },
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(end = 40.dp),
                //size(width = 250.dp, height = 30.dp),

                text = "維修工單內容",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = Color(255, 255, 255),
            )
        }
        Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .background(Color(255, 255, 255))) {
                RepairInfoTable(MA3_2_1_msggg,navController)

            }
        }
//        Text(text = "yoyo: ${RepairCode.toString()}")
    }
}

@Composable
fun RepairInfoTable(list: RepairInfoList,navController : NavHostController = rememberNavController()){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        //horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .bottomBorder(1.dp, Color(232, 232, 232)),
    )
    {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp), horizontalArrangement = Arrangement.SpaceBetween)
        {
            Text(
                text = "報修單號",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,

                color = Color(128,127,129),
                modifier = Modifier.padding( vertical = 10.dp),
                //.fillMaxWidth(),
                textAlign = TextAlign.Start,
            )
            Text(
                text = list.RepairCode,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(vertical = 10.dp),
                //.fillMaxWidth(),
                textAlign = TextAlign.Center,
            )
        }
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        //horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .bottomBorder(1.dp, Color(232, 232, 232)),
    )
    {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp), horizontalArrangement = Arrangement.SpaceBetween)
        {
            Text(
                text = "完成狀態",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,

                color = Color(128,127,129),
                modifier = Modifier.padding( vertical = 10.dp),
                //.fillMaxWidth(),
                textAlign = TextAlign.Start,
            )
            Text(
                text = list.State,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(vertical = 10.dp),
                textAlign = TextAlign.Center,
            )
        }
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        //horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .bottomBorder(1.dp, Color(232, 232, 232)),
    )
    {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp), horizontalArrangement = Arrangement.SpaceBetween)
        {
            Text(
                text = "指派人",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,

                color = Color(128,127,129),
                modifier = Modifier.padding( vertical = 10.dp),
                textAlign = TextAlign.Start,
            )
            Text(
                text = list.Manager,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(vertical = 10.dp),
                textAlign = TextAlign.Center,
            )
        }
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        //horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .bottomBorder(1.dp, Color(232, 232, 232)),
    )
    {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp), horizontalArrangement = Arrangement.SpaceBetween)
        {
            Text(
                text = "GIS X",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(128,127,129),
                modifier = Modifier.padding( vertical = 10.dp),
                textAlign = TextAlign.Start,
            )
            Text(
                text = list.Longitude,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(vertical = 10.dp),
                textAlign = TextAlign.Center,
            )
        }
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        //horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .bottomBorder(1.dp, Color(232, 232, 232)),
    )
    {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp), horizontalArrangement = Arrangement.SpaceBetween)
        {
            Text(
                text = "GIS Y",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,

                color = Color(128,127,129),
                modifier = Modifier.padding( vertical = 10.dp),
                textAlign = TextAlign.Start,
            )
            Text(
                text = list.Latitude,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(vertical = 10.dp),
                textAlign = TextAlign.Center,
            )
        }
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        //horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .bottomBorder(1.dp, Color(232, 232, 232)),
    )
    {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp), horizontalArrangement = Arrangement.SpaceBetween)
        {
            Text(
                text = "報修主旨",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(128,127,129),
                modifier = Modifier.padding( vertical = 10.dp),
                textAlign = TextAlign.Start,
            )
            Text(
                text = list.RepairTitle,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(vertical = 10.dp),
                textAlign = TextAlign.Center,
            )
        }
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        //horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .bottomBorder(1.dp, Color(232, 232, 232)),
    )
    {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp), horizontalArrangement = Arrangement.SpaceBetween)
        {
            Text(
                text = "報修說明",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,

                color = Color(128,127,129),
                modifier = Modifier.padding( vertical = 10.dp),
                textAlign = TextAlign.Start,
            )
            Text(
                text = list.RepairContent,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(vertical = 10.dp),
                textAlign = TextAlign.Center,
            )
        }
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        //horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    )
    {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp), horizontalArrangement = Arrangement.SpaceBetween)
        {
            Text(
                text = "照片",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(128,127,129),
                modifier = Modifier.padding( vertical = 10.dp),
                textAlign = TextAlign.Start,
            )
        }

    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        //horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, bottom = 15.dp)
    )
    {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp), horizontalArrangement = Arrangement.Center)
        {
            val imageBytes = Base64.decode(list.RepairPhoto, 0)
            val image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
            if(image != null)
            {
                Image(
                    modifier = Modifier.size(350.dp),
                    contentScale = ContentScale.FillWidth,
                    bitmap = image.asImageBitmap(),
                    contentDescription = "contentDescription")
            }
        }

    }
    RepairBottomBtn(MA3_2_1_msggg,navController);
}

@Composable
fun RepairBottomBtn(list: RepairInfoList,navController : NavHostController = rememberNavController()){
    Row(
        modifier = Modifier.fillMaxSize()
                           .padding(start = 20.dp,end = 20.dp,bottom = 20.dp)
                           .background(Color.Transparent),
        horizontalArrangement = Arrangement.SpaceAround, verticalAlignment = Alignment.Bottom
    ){
        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(86,107,183)),
            elevation = null,
            onClick =
            {

                Log.d("ButtonClick","1111111");
                navController.navigate(Screen.MA3_2_1_finishRepair.route)
            },
        )
        {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 3.dp),
            ){
                Icon(
                    modifier = Modifier
                        .size(25.dp),
                    imageVector = Icons.Default.Done,
                    contentDescription = "BackIcon",
                    tint = Color.White
                )
                Text(
                    text = "完工填報",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )
            }
        }
        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(86,107,183)),
            elevation = null,
            onClick =
            {
                val MA3_2_2_fullRoutePath = Screen.MA3_2_2.route + "?latitude=${list.Latitude}&longitude=${list.Longitude}&repairTitle=${list.RepairTitle}"
                Log.d("ButtonClick","地圖");
                navController.navigate(MA3_2_2_fullRoutePath);
            }

                )
        {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 3.dp),
            ){
                Icon(
                    modifier = Modifier
                        .size(25.dp),
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "BackIcon",
                    tint = Color.White
                )
                Text(
                    text = "地圖定位",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )
            }
        }
    }
}


//@Composable
//fun LazyColumn(content: List<RepairInfoList>){
//    LazyColumn(modifier = Modifier
//        .background(MaterialTheme.colors.background)
//        .fillMaxWidth()
//        .height(710.dp)){
//        items(content) { listContent ->
//            RepairInfoTable(listContent)
//        }
//    }
//}



//TODO:Jeremy增加
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MA3_2_1_MakeListCom(RepairCode:String?,State: String?){
    MA3_2_1_MakeList(RepairCode,State)
}
fun MA3_2_1_MakeList(RepairCode:String?,State: String?){
    GlobalScope.launch(Dispatchers.IO) {
        Log.d("RepairCode",RepairCode.toString())
        Log.d("State",State.toString())
        var RequestJsonObject = JSONObject();
        RequestJsonObject.put("Function", "RepairContent")
        RequestJsonObject.put("RepairCode", RepairCode.toString())
        RequestJsonObject.put("ReportType", "外巡報修")
        val responseString = HttpRequestTest(RequestJsonObject)
        Log.d("MA3_2_1",responseString)
        if(responseString!="Error"){
            var gson = Gson();
            var WorkInfoResponse:RepairContent_Response = gson.fromJson(responseString,RepairContent_Response::class.java)
            var workListDatas = MA3_2_1_msggg
            if(WorkInfoResponse.OutsideRepair != null){
                var outsiderepair = WorkInfoResponse.OutsideRepair;
                workListDatas = RepairInfoList(
                    outsiderepair.RepairCode.toString(),
                    State.toString(),
                    outsiderepair.Manager.toString(),
                    outsiderepair.Longitude.toString(),
                    outsiderepair.Latitude.toString(),
                    outsiderepair.RepairTitle.toString(),
                    outsiderepair.RepairContent.toString(),
                    outsiderepair.RepairPhoto.toString(),
                )
                if(outsiderepair.RepairPhoto.toString() == "")
                {
                    workListDatas.RepairPhoto = "/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAMCAgMCAgMDAwMEAwMEBQgFBQQEBQoHBwYIDAoMDAsKCwsNDhIQDQ4RDgsLEBYQERMUFRUVDA8XGBYUGBIUFRT/2wBDAQMEBAUEBQkFBQkUDQsNFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBT/wAARCAARABkDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD9U6KKKACiiigAooooAKKKKAP/2Q=="
                }
                Log.d("RepairPhoto",outsiderepair.RepairPhoto.toString())
            }
            MA3_2_1_msggg = workListDatas;
        }
//        val responsejsonobject = CustomMizeHttpRequestTest(RequestJsonObject)
//        val a:String? = responsejsonobject.get("DevRepair").toString()

    }
}
