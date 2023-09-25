package com.example.simpleprueba.core.usecase.impl

import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.view.LifecycleCameraController
import com.example.simpleprueba.core.repository.networks.PictureRepository
import com.example.simpleprueba.core.usecase.PictureService
import com.example.simpleprueba.data.dto.Constants
import com.example.simpleprueba.data.dto.FileResponse
import okhttp3.MultipartBody
import java.io.File
import java.util.Objects
import java.util.concurrent.Executor
import javax.inject.Inject

class PictureServicerImpl @Inject constructor(private val pictureRepository: PictureRepository) :
    PictureService {
    override fun takePicture(
        cameraController: LifecycleCameraController,
        executor: Executor
    ): File {
        val file = File.createTempFile(Constants.NAME_IMAGE, Constants.FILE_EXTENSION)
        val outputDirectory = ImageCapture.OutputFileOptions.Builder(file).build()
        cameraController.takePicture(
            outputDirectory,
            executor,
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    outputFileResults.savedUri
                    println(outputFileResults.savedUri)
                }

                override fun onError(exception: ImageCaptureException) {
                    println("succeed error!!")
                }
            })
        return file
    }

    override suspend fun sendPicture(files: List<MultipartBody.Part>): List<FileResponse> {
        var fileResponse: List<FileResponse> = listOf()
        try {
            val response = pictureRepository.sendPicture(files)
            if (response.isSuccessful && Objects.nonNull(response.body())) {
                fileResponse = response.body()!!
            }
        } catch (e: Exception) {
            println("En error en hacer la peticion del envio file${e.message}")
        }
        return fileResponse
    }
}