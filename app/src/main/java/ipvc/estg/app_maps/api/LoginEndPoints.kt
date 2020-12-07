package ipvc.estg.app_maps.api

import android.text.Editable
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface LoginEndPoints {

    @FormUrlEncoded
    @POST("/myslim/api/login/post")
    fun create(@Field("name") username: String,
               @Field("password") password: String?): Call<LoginOutputPost>

    @FormUrlEncoded
    @POST("myslim/api/login/create")
    fun postcreate(@Field("username") username: String,
                   @Field("password") password: String): Call<LoginOutputPost>

    @FormUrlEncoded
    @POST("myslim/api/ticket/create")
    fun create(@Field("username") username: String,
               @Field("tipo") tipo: String,
               @Field("texto") texto: Editable,
               @Field("local") local: String,
               @Field("foto") foto: String): Call<TicketOutputPost>
    @GET("myslim/api/tipo")
    fun tipo(): Call<List<Tipo>>
}