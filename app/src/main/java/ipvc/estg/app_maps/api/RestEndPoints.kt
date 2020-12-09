package ipvc.estg.app_maps.api

import retrofit2.Call
import retrofit2.http.*


interface RestEndPoints {

    @GET("/myslim/api/tickets")
    fun getUsers(): Call<List<User>>

    @GET("/myslim/api/filter/{id}")
    fun filter(@Path("id")id:Int): Call<List<User>>

    @FormUrlEncoded
    @POST("/myslim/api/postTicket")
    fun postTicket(@Field("id") ID: Int,
                 @Field("type") type: Int,
                 @Field("lat") lat: String,
                 @Field("lng") lng: String): Call<RestOutputPost>
}