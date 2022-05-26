package ir.rasadev.video.hiltdaggerproject.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import ir.rasadev.video.hiltdaggerproject.Constant.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class NoteModel (
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    var title: String = "default"
)