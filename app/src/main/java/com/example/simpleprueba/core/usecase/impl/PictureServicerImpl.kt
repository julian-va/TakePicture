package com.example.simpleprueba.core.usecase.impl

import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.view.LifecycleCameraController
import com.example.simpleprueba.core.usecase.PictureService
import com.example.simpleprueba.data.dto.Constants
import java.io.File
import java.util.concurrent.Executor

class PictureServicerImpl : PictureService {
    override fun takePicture(cameraController: LifecycleCameraController, executor: Executor) {
        val file = File.createTempFile(Constants.NAME_IMAGE, Constants.FILE_EXTENSION)
        val outputDirectory = ImageCapture.OutputFileOptions.Builder(file).build()
        cameraController.takePicture(
            outputDirectory,
            executor,
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    println(outputFileResults.savedUri)
                }

                override fun onError(exception: ImageCaptureException) {
                    println("succeed error!!")
                }
            })
    }
}