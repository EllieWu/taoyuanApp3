package com.elliewu.taoyuanapp3

import android.util.Log
import com.google.gson.Gson
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

suspend fun HttpRequestTest(jsonObject: JSONObject):String {
    // Create JSON using JSONObject

    // Convert JSONObject to String
    val jsonObjectString = jsonObject.toString()
    // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
    val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
    val client = OkHttpClient()
    val request = Request.Builder()
        .method("POST",requestBody)
        .url("http://api.taoyuan.isayso.de/api/app")
        .build()
    Log.d("HttpRequest","Start")
    try {
        var response = client.newCall(request).await();
        Log.d("HttpRequest","End")
        val abc = response.body?.string();
        //Log.d("HttpRequestResponse",abc)
        return  abc.toString();
    }catch (e:java.lang.Exception){
        Log.d("HttpRequestError",e.toString())
        return "Error"
    }
}
suspend fun CustomMizeHttpRequestTest(jsonObject: JSONObject):JSONObject {
    // Create JSON using JSONObject

    // Convert JSONObject to String
    val jsonObjectString = jsonObject.toString()
    // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
    val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
    val client = OkHttpClient()
    val request = Request.Builder()
        .method("POST",requestBody)
        .url("http://api.taoyuan.isayso.de/api/app")
        .build()
    Log.d("HttpRequest","Start")
    try {
        var response = client.newCall(request).await();
        Log.d("HttpRequest","End")
        val abc = response.body?.string();
        var gson = Gson();
        var WorkInfoResponse:JSONObject = gson.fromJson(abc,JSONObject::class.java)
        //Log.d("HttpRequestResponse",abc)
        return  WorkInfoResponse;
    }catch (e:java.lang.Exception){
        return JSONObject()
    }
}