package co.com.ceiba.mobile.pruebadeingreso.data.remote.model

import com.google.gson.annotations.SerializedName


data class PostEntry(
    @SerializedName("id") val id: Int,
    @SerializedName("userId") val userId: Int,
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String
)