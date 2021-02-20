package com.diegofajardo.notesapp.usecase

import com.diegofajardo.notesapp.data.repository.NoteRepository
import com.diegofajardo.notesapp.domain.NoteMobile

class GetNoteUsecase(
    private val noteRepository: NoteRepository
) {

    suspend fun invoke(id: Int): List<NoteMobile> {
        return noteRepository.findById(id)
    }

}