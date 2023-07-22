package com.example.photosearchapp.data.remote

import com.example.photosearchapp.common.constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface UnsplashApi {

    @Headers("Authorization: Client-ID $API_KEY")
    @GET("search/photos")
    suspend fun searchPhotos(@Query("query") query: String): SearchPhotosResultDto

    @Headers("Authorization: Client-ID $API_KEY")
    @GET("photos/{id}")
    suspend fun getPhotoById(@Path("id") photoId: String): PhotoDetailDto

}