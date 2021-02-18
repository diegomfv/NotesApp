package com.diegofajardo.notesapp.data.database

import androidx.room.*
import com.diegofajardo.notesapp.data.database.model.NoteDb

@Dao
interface NoteDao {

    @Query("SELECT * FROM NoteDb")
    fun getAllNotes(): List<NoteDb>

    @Query("SELECT * FROM NoteDb WHERE id = :id")
    fun findById(id: Int): List<NoteDb>

    @Query("SELECT COUNT(id) FROM NoteDb")
    fun notesCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNote(note: NoteDb)

    @Update
    fun updateNote(noteDb: NoteDb)

    @Query("DELETE FROM NoteDb WHERE id = :id")
    fun deleteNote(id: Int): Int
}