package ipvc.estg.app_maps.api

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginEndPoints {

    @FormUrlEncoded
    @POST("/myslim/api/login/create")
    fun create(@Field("name") username: String, @Field("password") password: String?): Call<LoginOutputPost>
}