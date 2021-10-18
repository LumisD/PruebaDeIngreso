package co.com.ceiba.mobile.pruebadeingreso.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity @JvmOverloads constructor(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "email") val email: String = "",
    @ColumnInfo(name = "phone") val phone: String = ""
)