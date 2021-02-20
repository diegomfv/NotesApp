package com.diegofajardo.notesapp.usecase

import com.diegofajardo.notesapp.data.repository.NoteRepository
import com.diegofajardo.notesapp.domain.NoteMobile

class SaveNoteUsecase(
    private val noteRepository: NoteRepository
) {

    suspend fun invoke(note: NoteMobile) {
        return noteRepository.saveNote(note)
    }
}