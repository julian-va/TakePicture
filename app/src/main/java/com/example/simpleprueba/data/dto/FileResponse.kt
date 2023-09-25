package com.example.simpleprueba.data.dto

import com.google.gson.annotations.SerializedName

data class FileResponse(@SerializedName(value = "file_name") val fileName: String = "")
