package com.diegofajardo.notesapp.usecase

import android.os.Build
import com.diegofajardo.notesapp.data.database.RoomDataSource
import com.diegofajardo.notesapp.data.database.model.NoteDb
import com.diegofajardo.notesapp.data.repository.NoteRepository
import com.diegofajardo.notesapp.shared.getInMemoryDatabase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Integration tests
 * */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class DeleteNoteUsecaseTest {

    @Test
    fun `invoke deletes the note that matches the query`() {
        val dao = getInMemoryDatabase().noteDao()
        val usecase = DeleteNoteUsecase(NoteRepository(RoomDataSource(dao)))

        runBlocking {

            /* Verify db is empty */
            assertEquals(dao.notesCount(), 0)

            /* Add some notes */
            dao.insertNote(NoteDb(0, "text1", true))
            dao.insertNote(NoteDb(0, "text2", false))

            /* Verify they were added */
            assertEquals(dao.notesCount(), 2)

            /* Verify note we are going to delete is in the database */
            assertEquals(dao.findById(1).size, 1)

            /* Run usecase */
            usecase.invoke(1)

            /* Verify note is deleted */
            assertEquals(dao.findById(1).size, 0)

            /* Verify other notes are still in database */
            val record = dao.findById(2)
            assertEquals(record.size, 1)
            assertNotNull(record.find { it.text == "text2" })

        }
    }
}