package com.diegofajardo.notesapp.usecase

import android.os.Build
import com.diegofajardo.notesapp.data.database.RoomDataSource
import com.diegofajardo.notesapp.data.database.model.NoteDb
import com.diegofajardo.notesapp.data.repository.NoteRepository
import com.diegofajardo.notesapp.shared.getInMemoryDatabase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Integration tests
 * */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class GetNoteUsecaseTest {

    @Test
    fun `invoke if database is empty does return empty list`() {
        val dao = getInMemoryDatabase().noteDao()
        val usecase = GetNoteUsecase(NoteRepository(RoomDataSource(dao)))

        runBlocking {

            /* Verify db is empty */
            assertEquals(dao.notesCount(), 0)

            /* Run usecase and verify data */
            val result = usecase.invoke(1)
            assertTrue(result.isEmpty())
        }
    }

    @Test
    fun `invoke if database is not empty does return all notes that match the query`() {
        val dao = getInMemoryDatabase().noteDao()
        val usecase = GetNoteUsecase(NoteRepository(RoomDataSource(dao)))

        runBlocking {

            /* Verify db is empty */
            assertEquals(dao.notesCount(), 0)

            /* Add some notes */
            dao.insertNote(NoteDb(0, "text1", true))
            dao.insertNote(NoteDb(0, "text2", false))

            /* Verify they were added */
            assertEquals(dao.notesCount(), 2)

            /* Run usecase and verify data */
            val result = usecase.invoke(1)
            assertEquals(result.size, 1)
            assertNotNull(result.find { it.text == "text1" })

            /* Run usecase and verify data */
            val result2 = usecase.invoke(2)
            assertEquals(result2.size, 1)
            assertNotNull(result2.find { it.text == "text2" })
        }
    }
}