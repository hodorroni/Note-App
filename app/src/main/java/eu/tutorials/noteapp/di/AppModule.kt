package eu.tutorials.noteapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import eu.tutorials.noteapp.data.NoteDatabase
import eu.tutorials.noteapp.data.NoteDatabaseDao
import javax.inject.Singleton


//we need an object to tell hilt how to provide instances of different types to create a room database
//need a builder to create an instance of room database which we dont want to be created each time the app starts

//will be created only once
@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    //will give us the Dao
    //@Provides - responsible for providing instances of objects that can be injected elsewhere in your application
    @Singleton
    @Provides
    fun provideNotesDao(noteDatabase: NoteDatabase) : NoteDatabaseDao
    = noteDatabase.noteDao()


    //will make the creation of the database
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) : NoteDatabase
    =Room.databaseBuilder(
        context,
        NoteDatabase::class.java,
        "notes_dbl"
    ).fallbackToDestructiveMigration()
        .build()
}