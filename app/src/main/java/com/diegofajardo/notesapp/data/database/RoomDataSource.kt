package com.diegofajardo.notesapp.data.database

import com.diegofajardo.notesapp.data.database.model.NoteDb
import com.diegofajardo.notesapp.data.toNoteDb
import com.diegofajardo.notesapp.data.toNoteMobile
import com.diegofajardo.notesapp.domain.NoteMobile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Room implementation of LocalDataSource
 * */
class RoomDataSource(private val noteDao: NoteDao) : LocalDataSource {

    override suspend fun isEmpty(): Boolean {
        return withContext(Dispatchers.IO) { noteDao.notesCount() <= 0 }
    }

    override suspend fun getAll(): List<NoteDb> {
        return withContext(Dispatchers.IO) { noteDao.getAllNotes() }
    }

    override suspend fun findById(id: Int): List<NoteMobile> {
        return withContext(Dispatchers.IO) { noteDao.findById(id).map { it.toNoteMobile() } }
    }

    override suspend fun countAll(): Int {
        return withContext(Dispatchers.IO) { noteDao.notesCount() }
    }

    override suspend fun saveNote(note: NoteMobile) {
        return withContext(Dispatchers.IO) { noteDao.insertNote(note.toNoteDb()) }
    }

    override suspend fun updateNote(note: NoteMobile) {
        return withContext(Dispatchers.IO) { noteDao.updateNote(note.toNoteDb()) }
    }

    override suspend fun deleteNote(id: Int) {
        return withContext(Dispatchers.IO) { noteDao.deleteNote(id) }
    }

}