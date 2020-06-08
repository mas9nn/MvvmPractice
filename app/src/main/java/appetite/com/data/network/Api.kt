package appetite.com.data.network

import appetite.com.data.network.responses.*
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("top-headlines")
    fun getAllShops(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Observable<HeadResponse>

    @GET("/api?%20appid=\$2y\$12\$esyosghhXSh6LxcX17N/suiqeJGJq/VQ9QkbqvImtE4JMWxz7WqYS&lang=ru&opt=view_cat&requests=no&cat_id=only_parent")
    fun getCategories(): Observable<MutableList<MainCategory>>

    @GET("/api?%20appid=\$2y\$12\$esyosghhXSh6LxcX17N/suiqeJGJq/VQ9QkbqvImtE4JMWxz7WqYS&lang=ru&opt=view_cat&cat_id=only_subcat&requests=no")
    fun getSubCategores(@Query("id") id: String): Observable<MutableList<MainCategory>>

    @GET("/api?appid=\$2y\$12\$esyosghhXSh6LxcX17N/suiqeJGJq/VQ9QkbqvImtE4JMWxz7WqYS&opt=view_task&tasks=all")
    fun getTasks(
        @Query("city") city: String,
        @Query("page") page: String
    ): Observable<MutableList<MainTasksItem>>

    @GET("/api?appid=\$2y\$12\$esyosghhXSh6LxcX17N/suiqeJGJq/VQ9QkbqvImtE4JMWxz7WqYS&lang=ru&opt=view_task")
    fun getTaskById(
        @Query("tasks") id: String
    ): Observable<MutableList<ExampleSubList>>

    @GET("api?appid=\$2y\$12\$esyosghhXSh6LxcX17N/suiqeJGJq/VQ9QkbqvImtE4JMWxz7WqYS&lang=ru&opt=view_user&param=more")
    fun getUser(
        @Query("user") id: String
    ): Observable<User>


    companion object {
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor,
            baseUrl: String
        ): Api {

            val okkHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okkHttpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(Api::class.java)
        }
    }
}