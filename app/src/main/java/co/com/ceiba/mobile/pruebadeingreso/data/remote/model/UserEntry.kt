package co.com.ceiba.mobile.pruebadeingreso.data.remote.model

import com.google.gson.annotations.SerializedName


data class UserEntry(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("phone") val phone: String,
)