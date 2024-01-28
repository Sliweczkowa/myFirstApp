package com.example.helloworld.di

import android.app.Application
import androidx.room.Room
import com.example.helloworld.data.PlaceDatabase
import com.example.helloworld.data.PlaceRepository
import com.example.helloworld.data.PlaceRepositoryImplementation
import com.example.helloworld.domain.useCase.DeletePlaceUseCase
import com.example.helloworld.domain.useCase.GetPlacesUseCase
import com.example.helloworld.domain.useCase.InsertPlaceUseCase
import com.example.helloworld.domain.useCase.PlaceUseCases
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

    @Provides
    @Singleton
    fun providePlaceUseCases(repository: PlaceRepository): PlaceUseCases {
        return PlaceUseCases(
            getPlaces = GetPlacesUseCase(repository),
            deletePlace = DeletePlaceUseCase(repository),
            insertPlace = InsertPlaceUseCase(repository)
        )
    }
}
