package com.example.assignment.data.di

import com.example.assignment.data.apps.Config
import com.example.assignment.data.networks.MyApi
import com.example.assignment.data.repository.ArtistRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModules {

    //providing methods which can be injected

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(Config.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun provideMyApi(retrofit: Retrofit):MyApi{
        return retrofit.create(MyApi::class.java)
    }

    @Singleton
    @Provides
    fun provideArtistRepository(myApi: MyApi): ArtistRepository{
        return ArtistRepository(myApi)
    }

}