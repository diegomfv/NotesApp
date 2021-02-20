package com.diegofajardo.notesapp.data.repository

import com.diegofajardo.notesapp.data.database.LocalDataSource
import com.diegofajardo.notesapp.domain.NoteMobile

/**
 * https://developer.android.com/jetpack/guide
 * Repository modules handle data operations. They provide a clean API so that the rest of the app
 * can retrieve this data easily. They know where to get the data from and what API calls to make
 * when data is updated. You can consider repositories to be mediators between different
 * data sources, such as persistent models, web services, and caches.
 *
 * Even though the repository module looks unnecessary, it serves an important purpose: it
 * abstracts the data sources from the rest of the app. Now, our ViewModel doesn't know
 * how the data is fetched, so we can provide the view model with data obtained from several
 * different data-fetching implementations.
 * */
class NoteRepository(
    private val localDataSource: LocalDataSource
) {

    suspend fun isEmpty(): Boolean = localDataSource.isEmpty()
    suspend fun getAll(): List<NoteMobile> = localDataSource.getAll()
    suspend fun findById(id: Int): List<NoteMobile> = localDataSource.findById(id)
    suspend fun countAll(): Int = localDataSource.countAll()
    suspend fun saveNote(note: NoteMobile)  = localDataSource.saveNote(note)
    suspend fun updateNote(note: NoteMobile) = localDataSource.updateNote(note)
    suspend fun deleteNote(id: Int) = localDataSource.deleteNote(id)

}

