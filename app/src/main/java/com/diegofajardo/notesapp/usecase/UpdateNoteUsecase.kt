package com.diegofajardo.notesapp.usecase

import com.diegofajardo.notesapp.data.repository.NoteRepository
import com.diegofajardo.notesapp.domain.NoteMobile

class UpdateNoteUsecase(
    private val repository: NoteRepository
) {

    suspend fun invoke(note: NoteMobile) {
        return repository.updateNote(note)
    }
}