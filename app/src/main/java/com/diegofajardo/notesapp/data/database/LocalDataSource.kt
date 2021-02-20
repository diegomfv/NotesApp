package com.diegofajardo.notesapp.data.database

import com.diegofajardo.notesapp.domain.NoteMobile

interface LocalDataSource {

    suspend fun isEmpty(): Boolean

    suspend fun getAll(): List<NoteMobile>
    suspend fun findById(id: Int): List<NoteMobile>
    suspend fun countAll(): Int

    suspend fun saveNote(note: NoteMobile)
    suspend fun updateNote(note: NoteMobile)
    suspend fun deleteNote(id: Int)

}