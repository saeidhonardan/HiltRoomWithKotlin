package ir.rasadev.video.hiltdaggerproject.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.rasadev.video.hiltdaggerproject.room.NoteDatabase
import ir.rasadev.video.hiltdaggerproject.room.NoteModel
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NoteModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context : Context)=
        Room.databaseBuilder(context,NoteDatabase::class.java,"note_db")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()


    @Provides
    @Singleton
    fun provideNoteDao(db: NoteDatabase) = db.noteDao()

    @Provides
    fun provideEntity() = NoteModel()
}