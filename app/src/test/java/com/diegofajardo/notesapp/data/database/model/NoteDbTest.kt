package com.diegofajardo.notesapp.data.database.model

import org.junit.Assert.assertEquals
import org.junit.Test

class NoteDbTest {

    @Test
    fun constructorTest() {
        val note = NoteDb(0, "test", true)
        assertEquals(note.id, 0)
        assertEquals(note.text, "test")
        assertEquals(note.isFavourite, true)
    }

}