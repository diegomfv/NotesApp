package com.diegofajardo.notesapp.data

import com.diegofajardo.notesapp.data.database.model.NoteDb
import com.diegofajardo.notesapp.domain.NoteMobile


fun NoteDb.toNoteMobile(): NoteMobile {
    return NoteMobile(id, text, isFavourite)
}

fun NoteMobile.toNoteDb() : NoteDb {
    return NoteDb(id, text, isFavourite)
}