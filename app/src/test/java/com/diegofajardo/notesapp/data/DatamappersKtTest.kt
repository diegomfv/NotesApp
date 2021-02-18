package com.diegofajardo.notesapp.data

import com.diegofajardo.notesapp.data.database.model.NoteDb
import com.diegofajardo.notesapp.domain.NoteMobile
import junit.framework.TestCase
import org.junit.Test

class DatamappersKtTest : TestCase() {

    @Test
    fun testToNoteMobile() {
        val noteDb = NoteDb(0, "test", false)

        val noteMobile = noteDb.toNoteMobile()
        assertEquals(noteMobile.id, 0)
        assertEquals(noteMobile.text, "test")
        assertEquals(noteMobile.isFavourite, false)
    }

    @Test
    fun testToNoteDb() {
        val noteMobile = NoteMobile(0, "test", false)

        val noteDb = noteMobile.toNoteDb()
        assertEquals(noteDb.id, 0)
        assertEquals(noteDb.text, "test")
        assertEquals(noteDb.isFavourite, false)
    }
}