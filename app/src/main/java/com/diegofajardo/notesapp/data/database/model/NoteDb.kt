package com.diegofajardo.notesapp.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Database model
 * */
@Entity
data class NoteDb(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val text: String,
    val isFavourite: Boolean
)
