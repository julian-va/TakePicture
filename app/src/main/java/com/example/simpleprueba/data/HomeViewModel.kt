package com.example.simpleprueba.data

import androidx.camera.view.LifecycleCameraController
import androidx.lifecycle.ViewModel
import com.example.simpleprueba.core.usecase.PictureService
import com.example.simpleprueba.core.usecase.impl.PictureServicerImpl
import java.util.concurrent.Executor

class HomeViewModel(private val pictureService: PictureService = PictureServicerImpl()) :
    ViewModel() {
    fun takePicture(cameraController: LifecycleCameraController, executor: Executor) {
        pictureService.takePicture(cameraController = cameraController, executor = executor)
    }
}