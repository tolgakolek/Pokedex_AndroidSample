package com.example.pokedex.di

import android.content.Context
import com.example.pokedex.data.local.AppDatabase
import com.example.pokedex.data.remote.service.PokemonService
import com.example.pokedex.data.remote.source.pokemon.PokemonRemoteDataSource
import com.example.pokedex.data.remote.source.pokemon.PokemonRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModul {
    private const val BASE_URL =
        "https://gist.githubusercontent.com"

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(moshiConverterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshiConverterFactory(): MoshiConverterFactory = MoshiConverterFactory.create()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }

    @Provides
    @Singleton
    fun providePokemonService(retrofit: Retrofit): PokemonService =
        retrofit.create(PokemonService::class.java)

    @Provides
    @Singleton
    fun providePokemonRemoteDataSource(pokemonService: PokemonService): PokemonRemoteDataSource =
    PokemonRemoteDataSourceImpl(pokemonService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun providePokemonDao(db: AppDatabase) = db.pokemonDao()

}