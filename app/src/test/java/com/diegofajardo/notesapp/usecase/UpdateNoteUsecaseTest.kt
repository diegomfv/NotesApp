package com.diegofajardo.notesapp.usecase

import android.os.Build
import com.diegofajardo.notesapp.data.database.RoomDataSource
import com.diegofajardo.notesapp.data.database.model.NoteDb
import com.diegofajardo.notesapp.data.repository.NoteRepository
import com.diegofajardo.notesapp.domain.NoteMobile
import com.diegofajardo.notesapp.shared.getInMemoryDatabase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class UpdateNoteUsecaseTest {

    @Test
    fun `invoke when query does not match does nothing` () {
        val dao = getInMemoryDatabase().noteDao()
        val usecase = UpdateNoteUsecase(NoteRepository(RoomDataSource(dao)))

        runBlocking {

            /* Verify db is empty */
            assertEquals(dao.notesCount(), 0)

            /* Add notes */
            dao.insertNote(NoteDb(0, "text1", true))
            dao.insertNote(NoteDb(0, "text2", false))

            /* Run usecase (update unexisting note) */
            usecase.invoke(NoteMobile(5, "textNew", false))

            /* Verify there are 2 records in db */
            assertEquals(dao.notesCount(), 2)

            /* Verify first record is not updated */
            val records = dao.findById(1)
            assertEquals(records.size, 1)
            assertNotNull(records.find { it.text == "text1" })
            assertNotNull(records.find { it.isFavourite })

            /* Verify second record is not updated */
            val records2 = dao.findById(2)
            assertEquals(records2.size, 1)
            assertNotNull(records2.find { it.text == "text2" })
            assertNotNull(records2.find { !it.isFavourite })

        }
    }

    @Test
    fun `invoke when query matches updates record in database`() {
        val dao = getInMemoryDatabase().noteDao()
        val usecase = UpdateNoteUsecase(NoteRepository(RoomDataSource(dao)))

        runBlocking {

            /* Verify db is empty */
            assertEquals(dao.notesCount(), 0)

            /* Add notes */
            dao.insertNote(NoteDb(0, "text1", true))
            dao.insertNote(NoteDb(0, "text2", false))

            /* Run usecase (update first record) */
            usecase.invoke(NoteMobile(1, "textNew", false))

            /* Verify first record is updated */
            val records = dao.findById(1)
            assertEquals(records.size, 1)
            assertNotNull(records.find { it.text == "textNew" })
            assertNotNull(records.find { !it.isFavourite })

            /* Verify second record is not updated */
            val records2 = dao.findById(2)
            assertEquals(records2.size, 1)
            assertNotNull(records2.find { it.text == "text2" })
            assertNotNull(records2.find { !it.isFavourite })

        }
    }
}