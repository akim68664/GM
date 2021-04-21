package com.example.assignment.data.networks

import com.example.assignment.data.Utils.ApiException
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response


// if the response is successful, we return the ResponseBody,
// otherwise return errorBody and return ApiException message
abstract class SafeApiRequest {

    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {
        val response = call.invoke()

        if (response.isSuccessful) {
            return response.body()!!
        } else {
            val error = response.errorBody()?.toString()
            val message = StringBuilder()
            error?.let {
                try {
                    message.append(JSONObject(it).getString("message"))
                } catch (e: JSONException) {
                    message.append("\n")
                }
            }
            message.append("Error Code : ${response.code()}")

            throw ApiException(message.toString())
        }
    }
}