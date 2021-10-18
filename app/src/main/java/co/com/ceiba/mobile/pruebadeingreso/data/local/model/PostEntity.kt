package co.com.ceiba.mobile.pruebadeingreso.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post")
data class PostEntity @JvmOverloads constructor(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "user_id") val userId: Int = 0,
    @ColumnInfo(name = "title") val title: String = "",
    @ColumnInfo(name = "body") val body: String = ""
)