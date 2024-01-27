package com.example.helloworld.di

import android.app.Application
import androidx.room.Room
import com.example.helloworld.data.PlaceDatabase
import com.example.helloworld.data.PlaceRepository
import com.example.helloworld.data.PlaceRepositoryImplementation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePlaceDatabase(app: Application): PlaceDatabase {
        return Room.databaseBuilder(
            app,
            PlaceDatabase::class.java,
            PlaceDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providePlaceRepository(db: PlaceDatabase): PlaceRepository {
        return PlaceRepositoryImplementation(db.placeDao)
    }
}
