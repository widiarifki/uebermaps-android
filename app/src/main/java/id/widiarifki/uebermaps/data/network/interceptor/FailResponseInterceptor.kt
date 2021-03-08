package id.widiarifki.uebermaps.data.network.interceptor

import android.content.Context
import com.google.gson.JsonParser
import id.widiarifki.uebermaps.R
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class FailResponseInterceptor(val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val responseBody = chain.proceed(request).body
        val responseAsObj = JsonParser.parseString(responseBody?.string()).asJsonObject
        if (responseAsObj.has("errors")) {
            val errors = responseAsObj.get("errors").asJsonArray
            val firstErrorObj = errors.get(0)?.asJsonObject
            if (firstErrorObj?.has("title") == true) {
                throw IOException(
                    firstErrorObj.get("title")?.asString ?: context.getString(R.string.msg_error_global)
                )
            }
        }

        val newRequest = request.newBuilder().build()
        return chain.proceed(newRequest)
    }

}
