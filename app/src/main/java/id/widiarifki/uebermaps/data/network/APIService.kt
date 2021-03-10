package id.widiarifki.uebermaps.data.network

import android.content.Context
import id.widiarifki.uebermaps.BuildConfig
import id.widiarifki.uebermaps.data.model.Attachment
import id.widiarifki.uebermaps.data.model.Maps
import id.widiarifki.uebermaps.data.model.Spot
import id.widiarifki.uebermaps.data.model.User
import id.widiarifki.uebermaps.data.network.interceptor.ConnectivityInterceptor
import id.widiarifki.uebermaps.data.network.response.wrapper.ListResponse
import id.widiarifki.uebermaps.data.network.response.wrapper.ObjectResponse
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface APIService {

    @GET("authentication")
    suspend fun loginUser(
        @Query("user[email]") email: String?,
        @Query("user[password]") password: String?
    ) : ObjectResponse<User>

    @GET("trends/recommended")
    suspend fun getRecommendedMaps(
        @Query("page") page: Int? = 1,
        @Query("count") count: Int? = 10
    ) : ListResponse<Maps>

    @GET("trends/latest")
    suspend fun getLatestMaps(
        @Query("page") page: Int? = 1,
        @Query("count") count: Int? = 10
    ) : ListResponse<Maps>

    @GET("users/{user_id}/maps")
    suspend fun getMyMaps(
        @Path("user_id") user_id: Int?,
        @Query("page") page: Int? = 1,
        @Query("count") count: Int? = 10
    ) : ListResponse<Maps>

    @GET("maps/{id}")
    suspend fun getMapDetail(
        @Path("id") id: Int?
    ) : ObjectResponse<Maps>

    @GET("maps/{id}/spots")
    suspend fun getMapSpots(
        @Path("id") id: Int?,
        @Query("page") page: Int? = 1,
        @Query("count") count: Int = 5
    ) : ListResponse<Spot>

    @GET("maps/{id}/attachments")
    suspend fun getMapAttachments(
        @Path("id") id: Int?,
        @Query("page") page: Int? = 1,
        @Query("count") count: Int = 15
    ) : ListResponse<Attachment>

    companion object {
        fun create(context: Context) : APIService {
            val httpClient = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .connectionPool(ConnectionPool(0, 1, TimeUnit.NANOSECONDS))
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .addInterceptor(ConnectivityInterceptor(context))
                //.addInterceptor(FailResponseInterceptor(context))
                .build()

            return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build()
                .create(APIService::class.java)
        }
    }
}