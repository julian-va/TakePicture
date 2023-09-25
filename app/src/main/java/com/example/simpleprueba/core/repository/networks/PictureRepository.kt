package com.example.simpleprueba.core.repository.networks

import com.example.simpleprueba.data.dto.FileResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface PictureRepository {
    @POST(value = "upload")
    @Multipart
    suspend fun sendPicture(@Part files: List<MultipartBody.Part>): Response<List<FileResponse>>
}