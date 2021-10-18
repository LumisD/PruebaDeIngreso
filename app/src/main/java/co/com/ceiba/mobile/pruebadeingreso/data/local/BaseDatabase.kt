package co.com.ceiba.mobile.pruebadeingreso.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import co.com.ceiba.mobile.pruebadeingreso.data.local.model.PostEntity
import co.com.ceiba.mobile.pruebadeingreso.data.local.model.UserEntity

@Database(
    entities = [
        UserEntity::class,
        PostEntity::class
    ], version = 1, exportSchema = false
)
abstract class BaseDatabase : RoomDatabase() {

    abstract fun baseDao(): BaseDao

}