package com.example.simpleprueba.data

import androidx.camera.view.LifecycleCameraController
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpleprueba.core.usecase.PictureService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.util.concurrent.Executor
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val pictureService: PictureService) :
    ViewModel() {
    private val uriList = mutableListOf<File>()
    fun takePicture(cameraController: LifecycleCameraController, executor: Executor) {
        val file =
            pictureService.takePicture(cameraController = cameraController, executor = executor)
        file?.let { file -> uriList.add(file) }
        sendPicture()
    }

    private fun sendPicture() {
        viewModelScope.launch(Dispatchers.IO) {
            val files = uriList.map { file ->
                var requestBody = RequestBody.create(MediaType.parse("multipart form-data"), file)
                MultipartBody.Part
                    .createFormData(
                        "files",
                        file.name,
                        requestBody
                    )
            }.toList()
            println(files)
            val res = pictureService.sendPicture(files = files)
            println(res)
        }
    }
}