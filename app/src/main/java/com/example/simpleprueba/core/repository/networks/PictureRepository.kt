package com.example.simpleprueba.core.repository.networks

import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface PictureRepository {
    @POST(value = "/upload-file")
    @Multipart
    suspend fun senPicture(@Part files: MultipartBody.Part): Response<Unit>
}