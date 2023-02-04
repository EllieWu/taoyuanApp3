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
suspend fun HttpRequestTest(jsonObject: JSONObject):String {
    // Create JSON using JSONObject

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