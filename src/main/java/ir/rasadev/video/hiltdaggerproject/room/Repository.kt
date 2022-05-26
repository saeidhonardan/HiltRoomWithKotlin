package ir.rasadev.video.hiltdaggerproject.room

import javax.inject.Inject

class Repository @Inject constructor(private val dao : NoteDao) {
    fun saveNote(entity : NoteModel)= dao.insertNote(entity)
    fun getAllNote(): MutableList<NoteModel> = dao.getAllNote()
}