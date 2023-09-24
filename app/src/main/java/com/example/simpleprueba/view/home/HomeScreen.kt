package com.example.simpleprueba.view.home

import android.Manifest
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.core.content.ContextCompat
import com.example.simpleprueba.data.HomeViewModel
import com.example.simpleprueba.view.composables.CameraComposable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(homeViewModel: HomeViewModel = HomeViewModel()) {
    val context = LocalContext.current
    val lifecycle = LocalLifecycleOwner.current
    val executor = ContextCompat.getMainExecutor(context)
    val cameraController = remember {
        LifecycleCameraController(context)
    }

    val permissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)
    LaunchedEffect(Unit) {
        permissionState.launchPermissionRequest()
    }

    Scaffold(modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = {
                homeViewModel.takePicture(cameraController = cameraController, executor = executor)
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "take picture")
            }
        }
    ) {
        if (permissionState.status.isGranted) {
            CameraComposable(
                modifier = Modifier.padding(it),
                cameraController = cameraController,
                lifecycle
            )
        } else {
            Text(text = "Permiso Denegado", Modifier.padding(it))
        }

    }
}

