package com.yonder.addtolist.core.di.modules

import android.content.Context
import androidx.room.Room
import com.yonder.addtolist.BuildConfig
import com.yonder.addtolist.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Yusuf Onder on 12,May,2021
 */
@[Module InstallIn(SingletonComponent::class)]
object RoomModule {

  @Provides
  @Singleton
  fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
    return Room.databaseBuilder(context, AppDatabase::class.java, BuildConfig.DATABASE_NAME)
      .fallbackToDestructiveMigration()
      .build()
  }

}