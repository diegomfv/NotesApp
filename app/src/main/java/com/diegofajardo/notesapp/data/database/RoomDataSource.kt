package com.diegofajardo.notesapp.data.database

import com.diegofajardo.notesapp.data.toNoteDb
import com.diegofajardo.notesapp.data.toNoteMobile
import com.diegofajardo.notesapp.domain.NoteMobile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Room implementation of LocalDataSource
 *
 * A side note here.
 * Mapping at this stage IS DONE FOR SIMPLIFICATION but can involve some problems:
 * 1. We can have a performance penalty if another repository needs access to this data in order to
 * perform a mapping operation. It would be better to use the DbModel instead of the MobileModel and
 * skip the mapping operation at this point.
 * 2. We might need some extra data to do a mapping operation: eg. it.toMobileModel(extraData).
 * If that data were provided by another dataSource we would have a problem.
 * As a rule of thumb the mapping operations should be done the later the possible.
 * */
class RoomDataSource(private val noteDao: NoteDao) : LocalDataSource {

    override suspend fun isEmpty(): Boolean {
        return withContext(Dispatchers.IO) { noteDao.notesCount() <= 0 }
    }

    override suspend fun getAll(): List<NoteMobile> {
        return withContext(Dispatchers.IO) { noteDao.getAllNotes().map { it.toNoteMobile() } }
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