package com.elliewu.taoyuanapp3

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.coroutines.*
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.POST
import ru.gildor.coroutines.okhttp.await
import java.io.IOException

interface APIService {
    @POST("/api/app/")
    suspend fun createEmployee(@Body requestBody: RequestBody): retrofit2.Response<ResponseBody>
}
fun rawJSON(PostjsonBody : JSONObject): String? {

    // Create Retrofit
    val retrofit = Retrofit.Builder()
        .baseUrl("http://api.taoyuan.isayso.de/")
        .build()

    // Create Service
    val service = retrofit.create(APIService::class.java)

    // Create JSON using JSONObject

    var jsonObject = JSONObject()
        jsonObject = PostjsonBody;

    // Convert JSONObject to String
    val jsonObjectString = jsonObject.toString()

    // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
    val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

    var returnJsonString:String = "";
    var abccc = CoroutineScope(Dispatchers.IO).launch {
        // Do the POST request and get response
        val response = service.createEmployee(requestBody)

        withContext(Dispatchers.Main) {
            if (response.isSuccessful) {

                // Convert raw JSON to pretty JSON using GSON library
                val gson = GsonBuilder().setPrettyPrinting().create()
                val prettyJson = gson.toJson(
                    JsonParser.parseString(
                        response.body()
                            ?.string() // About this thread blocking annotation : https://github.com/square/retrofit/issues/3255
                    )
                )
                returnJsonString = prettyJson;
                return@withContext
            } else {
                returnJsonString = ""
                Log.e("RETROFIT_ERROR", response.code().toString())
                return@withContext
            }
        }

    }
    Log.d("Pretty Printed :", abccc.toString())
    return returnJsonString
}
suspend fun HttpRequestTest():String {
    var loginJsonObject = JSONObject();
    loginJsonObject.put("Function", "Login")
    loginJsonObject.put("UserID", "F123332212")
    loginJsonObject.put("UserPW", "Abc1234")
    // Create JSON using JSONObject

    var jsonObject = loginJsonObject

    // Convert JSONObject to String
    val jsonObjectString = jsonObject.toString()
    var resList = ArrayList<String>()
    var responseString = "";
    // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
    val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
    val client = OkHttpClient()
    val request = Request.Builder()
        .method("POST",requestBody)
        .url("http://api.taoyuan.isayso.de/api/app")
        .build()
    var response = client.newCall(request).await();

    return  response.body?.string().toString();
}