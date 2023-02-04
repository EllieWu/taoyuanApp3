package com.elliewu.taoyuanapp3

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.POST

interface APIService {
    @POST("/api/app/")
    suspend fun createEmployee(@Body requestBody: RequestBody): retrofit2.Response<ResponseBody>
}
fun rawJSON(PostjsonBody : JSONObject): JSONObject? {

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

    var returnJsonObject = JSONObject();
    CoroutineScope(Dispatchers.IO).launch {
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
                returnJsonObject = JSONObject(prettyJson);
                Log.d("Pretty Printed JSON :", prettyJson)
            } else {

                Log.e("RETROFIT_ERROR", response.code().toString())
                returnJsonObject = JSONObject()
            }
        }
    }
    return returnJsonObject
}