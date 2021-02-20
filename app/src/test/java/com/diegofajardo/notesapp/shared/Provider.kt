package com.diegofajardo.notesapp.shared

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.diegofajardo.notesapp.data.database.NoteDatabase

fun getInMemoryDatabase(): NoteDatabase {
    return Room.inMemoryDatabaseBuilder(
        InstrumentationRegistry.getInstrumentation().targetContext,
        NoteDatabase::class.java
    )
        .allowMainThreadQueries()
        .build()
}