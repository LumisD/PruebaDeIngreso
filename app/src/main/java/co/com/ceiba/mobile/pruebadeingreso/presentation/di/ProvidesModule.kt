package co.com.ceiba.mobile.pruebadeingreso.presentation.di

import android.content.Context
import androidx.room.Room
import co.com.ceiba.mobile.pruebadeingreso.data.local.BaseDatabase
import co.com.ceiba.mobile.pruebadeingreso.data.remote.Endpoints.URL_BASE
import co.com.ceiba.mobile.pruebadeingreso.data.remote.RestApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ProvidesModule {

    @Singleton
    @Provides
    fun provideCache(cacheFile: File): Cache {
        return Cache(cacheFile, 10 * 1000 * 1000L) //10 MB
    }

    @Singleton
    @Provides
    fun provideFile(@ApplicationContext context: Context): File {
        val file = File(context.cacheDir, "HttpCache")
        file.mkdirs()
        return file
    }

    @Singleton
    @Provides
    fun provideCurrencyRestApi(cache: Cache): RestApi {
        val okHttpBuilder = OkHttpClient
            .Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .cache(cache)

        return Retrofit.Builder()
            .client(okHttpBuilder.build())
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(RestApi::class.java)
    }


    @Singleton
    @Provides
    fun provideBaseDataBase(@ApplicationContext context: Context): BaseDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            BaseDatabase::class.java,
            "base.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideBaseDao(db: BaseDatabase) = db.baseDao()
}



