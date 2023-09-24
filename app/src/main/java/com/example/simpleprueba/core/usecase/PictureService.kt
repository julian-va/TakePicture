package com.example.simpleprueba.core.usecase

import androidx.camera.view.LifecycleCameraController
import java.util.concurrent.Executor

@FunctionalInterface
interface PictureService {
    fun takePicture(cameraController: LifecycleCameraController, executor: Executor)
}