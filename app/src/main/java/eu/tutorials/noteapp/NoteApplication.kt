package eu.tutorials.noteapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//this gives Hilt access to the entire application, that's why we inherit from Application
//which is the highest hierarchy layer

//now Hilt can supply dependencies to any part of the app
@HiltAndroidApp
class NoteApplication: Application() {
}