package com.elliewu.taoyuanapp3

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import kotlinx.coroutines.launch
import org.json.JSONObject

@Preview(device = Devices.PIXEL_C)
@Preview(device = Devices.PIXEL_3A)
@Preview(showBackground = true)
@Composable
fun changePassword_newPassword(
    navController: NavHostController = rememberNavController()
) {
    val coroutineScope = rememberCoroutineScope()
    PwderrorDialog();
    loadingDialog()
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
            ClickableText(
                text = AnnotatedString("返回"),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 20.sp,
                    textAlign = TextAlign.End,
                    textDecoration = TextDecoration.Underline,
                ),
                onClick = {
                    navController.navigate(Screen.changePassword.route)
                },
                modifier = Modifier.padding(start = 20.dp)
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(end = 60.dp),
                //size(width = 250.dp, height = 30.dp),
                text = "密碼變更",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = Color(255, 255, 255),
            )
        }
        Column(modifier = Modifier.padding(top = 120.dp, start = 60.dp, end = 60.dp)) {
            Text("輸入新密碼",fontSize = 24.sp,textAlign = TextAlign.Start,fontWeight = FontWeight.Bold,
                color = Color(62, 83, 140),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp))

            var New_password by remember { mutableStateOf("") }
            val maxLength = 15
            TextField( modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
                value = New_password,
                singleLine = true,
                maxLines = 1,
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = Color.Black,
                    backgroundColor = Color(255,255,255),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent), onValueChange = {if (it.length <= maxLength) New_password = it})

            Text("再次確認新密碼",fontSize = 24.sp,textAlign = TextAlign.Start,fontWeight = FontWeight.Bold,
                color = Color(62, 83, 140),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp))
            var New_password_Checked by remember { mutableStateOf("") }
            TextField( modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp),
                value = New_password_Checked,
                singleLine = true,
                maxLines = 1,
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = Color.Black,
                    backgroundColor = Color(255,255,255),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent), onValueChange = {if (it.length <= maxLength) New_password_Checked = it})

            Button(colors = ButtonDefaults.buttonColors(backgroundColor = Color(red = 87, green = 104, blue = 182)),
                modifier = Modifier
                    .fillMaxWidth()
                    .size(width = 50.dp, height = 60.dp),
                onClick = {
                    if(New_password == New_password_Checked)
                    {
                        //TODO :修改成功後跳回首頁
                        coroutineScope.launch {
                            var loginJsonObject = JSONObject();
                            loginJsonObject.put("Function", "ChangePassword")
                            //TODO : 正式上線請把預設值改掉換下面那個
                            loginJsonObject.put("UserID", Login_UserId)
                            loginJsonObject.put("UserNewPW", New_password)
                            //loginJsonObject.put("UserID", "F123332212")
                            //loginJsonObject.put("UserNewPW", "Abc1234")
                            showDialog = true
                            val responseString = HttpRequestTest(loginJsonObject)
                            Log.d("ChangePassword",responseString)
                            if(responseString == "Error")
                            {
                                showDialog = false
                                //TODO :網路連線異常的通知
                                ifError = "0"
                                openDialog = true
                            }
                            else{
                                val jResponse = JSONObject(responseString);
                                val succeed:String? = jResponse.getString("Feedback").toString();
                                if(succeed == "TRUE")
                                {
                                    navController.navigate(Screen.login.route)
                                    ifError = ""
                                }
                                showDialog = false
                            }
                        }
                    }
                    else{
                        ifError = "1"
                        openDialog = true
                    }

                },) {
                Text(text = "儲存更改", fontSize = 24.sp, fontWeight = FontWeight.Bold,
                    color = Color(255,255,255),
                )
            }
        }

    }
    BottomSpace(navController);
}
@Composable
fun PwderrorDialog(){
    Column {

        Button(onClick = {
            openDialog = true
        }) {
            Text("Click me")
        }

        if (openDialog) {

            AlertDialog(
                onDismissRequest = {
                    // Dismiss the dialog when the user clicks outside the dialog or on the back
                    // button. If you want to disable that functionality, simply use an empty
                    // onCloseRequest.
                    openDialog = false
                },
                title = {
                    Text(text = "提示訊息!")
                },
                text = {
                    if(ifError == "0"){
                        Text("更改失敗，請檢查網路設置")
                    }else if(ifError == "1"){
                        Text("請驗證新密碼與確認新密碼是否相同")
                    }

                },
                confirmButton = {
                    Button(

                        onClick = {
                            openDialog = false
                        }) {
                        Text("確認")
                    }
                },
//                dismissButton = {
//                    Button(
//
//                        onClick = {
//                            openDialog = false
//                        }) {
//                        Text("返回")
//                    }
//                }
            )
        }
    }
}