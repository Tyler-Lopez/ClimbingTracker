package com.climbingtrackerapp.data

import retrofit2.http.*

interface AthleteApi {
    @POST("oauth/token?")
    suspend fun getAccessTokenFromAuthCode() // todo

    @POST("oauth/token?")
    suspend fun getAccessTokenFromRefreshToken() // todo

    @GET("api/v3/activities/{id}")
    suspend fun getActivity(
        @Header("Authorization") authHeader: String,
        @Path("id") id: String
    )

    @POST("api/v3/activities")
    suspend fun createActivity(
        @Header("Authorization") authHeader: String,
        @Query("name") name: String,
        @Query("sport_type") sportType: String,
        @Query("start_date_local") startDateLocal: String,
        @Query("elapsed_time") elapsedTime: Int,
        @Query("description") description: String
    )
}
