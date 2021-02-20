package com.diegofajardo.notesapp.usecase

import com.diegofajardo.notesapp.data.repository.NoteRepository

class CountNotesUsecase(
    private val repository: NoteRepository
) {

    suspend fun invoke(): Int {
        return repository.countAll()
    }
}