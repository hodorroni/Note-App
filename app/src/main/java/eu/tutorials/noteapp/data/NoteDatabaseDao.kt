package eu.tutorials.noteapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import eu.tutorials.noteapp.model.Note
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDatabaseDao {
    //when we will execute getNotes it will do the SELECT * FROM our table
    @Query("SELECT * from notes_tbl")
    fun getNotes(): Flow<List<Note>>

    @Query("SELECT * from notes_tbl where id=:id")
    suspend fun getNoteById(id:String):Note

    //if something goes wrong when inserting into the table we just gonna replace it and add the new one
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note:Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(note:Note)

    @Query("DELETE from notes_tbl")
    suspend fun deleteAll()

    //delete a specific note in the db
    @Delete
    suspend fun deleteNote(note: Note)

}
