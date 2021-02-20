package com.diegofajardo.notesapp.usecase

import com.diegofajardo.notesapp.data.repository.NoteRepository

class DeleteNoteUsecase(
    private val noteRepository: NoteRepository
) {

    suspend fun invoke(id: Int) {
        return noteRepository.deleteNote(id)
    }

}