package com.diegofajardo.notesapp.usecase

import com.diegofajardo.notesapp.data.repository.NoteRepository
import com.diegofajardo.notesapp.domain.NoteMobile

class GetAllNotesUsecase(
    private val noteRepository: NoteRepository
) {

    suspend fun invoke(): List<NoteMobile> {
        return noteRepository.getAll()
    }

}