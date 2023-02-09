package com.elliewu.taoyuanapp3

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
//TODO:未來請將預設UserId設為空字串
var Login_UserId by mutableStateOf("F123332212");
@Preview(device= Devices.PIXEL_C)
@Preview(device= Devices.PIXEL_3A)
@Composable
fun login(navController: NavHostController = rememberNavController(), onClick: () -> Unit = {}){
    Log.d("Login","APP仔入中")
    val coroutineScope = rememberCoroutineScope()
    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        ){
        Image(painter = painterResource(id = R.drawable.login_bg),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth())
        Row(horizontalArrangement = Arrangement.Center,modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()){
            Column(
                modifier = Modifier
                    .size(width = 500.dp, 1000.dp)
                    .padding(start = 50.dp, end = 50.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
               ) {
                Image(
                    painterResource(id = R.drawable.logo), contentDescription = "",
                    modifier = Modifier
                        //.fillMaxWidth()
                        .padding(bottom = 20.dp))
                Text("智慧巡檢系統", fontSize = 24.sp, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold,
                    color = Color(180,203,237),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 30.dp))
                Text("帳號",fontSize = 24.sp,textAlign = TextAlign.Start,fontWeight = FontWeight.Bold,
                    color = Color(255,255,255),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp))

                var account by remember { mutableStateOf("") }
                val maxLength = 110
                TextField( modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                    value = account,
                    colors = TextFieldDefaults.textFieldColors(
                        cursorColor = Color.Black,
                        backgroundColor = Color(255,255,255),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent), onValueChange = {if (it.length <= maxLength) account = it})

                Text("密碼",fontSize = 24.sp,textAlign = TextAlign.Start,fontWeight = FontWeight.Bold,
                    color = Color(255,255,255),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp))

                var password by remember { mutableStateOf("") }
                TextField( modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 40.dp),
                    value = password,
                    colors = TextFieldDefaults.textFieldColors(
                        cursorColor = Color.Black,
                        backgroundColor = Color(255,255,255),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent), onValueChange = {if (it.length <= maxLength) password = it})

                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(
                            red = 255,
                            green = 166,
                            blue = 0
                        )
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(width = 50.dp, height = 60.dp),
                    onClick = {
                        //TODO:之後須在外層補入請輸入帳號/請輸入密碼
                        Log.d("Login","嘗試登入");
                        coroutineScope.launch {
                            var loginJsonObject = JSONObject();
                            loginJsonObject.put("Function", "Login")
                            //TODO : 正式上線請把預設值改掉換下面那個
                            //loginJsonObject.put("UserID", account)
                            //loginJsonObject.put("UserPW", password)
                            //Login_UserId = account
                            loginJsonObject.put("UserID", "F123332212")
                            loginJsonObject.put("UserPW", "Abc1234")
                            val responseString = HttpRequestTest(loginJsonObject)
                            Log.d("Login Response",responseString)
                            if(responseString == "Error")
                            {
                                //TODO :網路連線異常的通知
                            }
                            else{
                                val jResponse = JSONObject(responseString);
                                val succeed:String? = jResponse.getString("Feedback").toString();
                                if(succeed == "TRUE")
                                {
                                    //Login_UserId = account;
                                    Login_UserId = "F123332212";
                                    navController.navigate(Screen.MA3_1.route)
                                }
                                else
                                {
                                    //TODO:登入失敗跳的東西
                                }
                            }
                        }
                    },
                ) {
                    Text(text = "登入", fontSize = 24.sp, fontWeight = FontWeight.Bold,
                        color = Color(255,255,255)
                    )
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewLogin() {
    login();
}