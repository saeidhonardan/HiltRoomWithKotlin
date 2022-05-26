package ir.rasadev.video.hiltdaggerproject.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.rasadev.video.hiltdaggerproject.Constant

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(model : NoteModel)

    @Query("SELECT * FROM ${Constant.TABLE_NAME} ORDER BY id  DESC")
    fun getAllNote() : MutableList<NoteModel>
}