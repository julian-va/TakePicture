package com.example.simpleprueba.core.usecase

import androidx.camera.view.LifecycleCameraController
import com.example.simpleprueba.data.dto.FileResponse
import okhttp3.MultipartBody
import java.io.File
import java.util.concurrent.Executor


interface PictureService {
    fun takePicture(cameraController: LifecycleCameraController, executor: Executor): File
    suspend fun sendPicture(files: List<MultipartBody.Part>): List<FileResponse>
}