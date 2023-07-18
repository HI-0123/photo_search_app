package com.example.photosearchapp.data.remote

import com.example.photosearchapp.common.constants
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface UnsplashApi {

    @Headers("Authorization: Client-ID ${constants.api_key}")
    @GET("search/photos")
    suspend fun searchPhotos(@Query("query") query: String): SearchPhotosResultDto

    @Headers("Authorization: Client-ID ${constants.api_key}")
    @GET("photos/{id}")
    suspend fun getPhotoById(@Path("id") photoId: String): PhotoDetailDto

}