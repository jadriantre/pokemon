package com.example.pokemon.core

import android.content.Context
import com.example.pokemon.core.util.InternetConnectionHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InternetConnectionModule {
    @Singleton
    @Provides
    fun getInformation( @ApplicationContext context: Context) = InternetConnectionHelper(context)
}