package com.example.daggerhiltroomdb

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModul {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context) : UserDatabase =
        Room.databaseBuilder(context, UserDatabase::class.java, "userDB")
            .build()

    @Provides
    fun providesUserDao(userDatabase: UserDatabase) = userDatabase.userDao()

    @Provides
    fun providesRepository(userDao: UserDao): UserRepository = UserRepository(userDao)
}