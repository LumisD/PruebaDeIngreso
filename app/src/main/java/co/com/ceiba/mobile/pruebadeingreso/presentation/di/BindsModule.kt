package co.com.ceiba.mobile.pruebadeingreso.presentation.di

import co.com.ceiba.mobile.pruebadeingreso.data.local.ILocalSource
import co.com.ceiba.mobile.pruebadeingreso.data.local.LocalSourceImpl
import co.com.ceiba.mobile.pruebadeingreso.data.remote.IRemoteSource
import co.com.ceiba.mobile.pruebadeingreso.data.remote.RemoteSourceImpl
import co.com.ceiba.mobile.pruebadeingreso.data.repositoryimpl.BaseDataRepository
import co.com.ceiba.mobile.pruebadeingreso.domain.IBaseRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class BindsModule {

    @Binds
    abstract fun provideBaseRepository(repository: BaseDataRepository): IBaseRepository

    @Binds
    abstract fun provideLocalSource(source: LocalSourceImpl): ILocalSource

    @Binds
    abstract fun provideRemoteSource(source: RemoteSourceImpl): IRemoteSource
}
