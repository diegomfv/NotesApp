package com.diegofajardo.notesapp.usecase

import android.os.Build
import com.diegofajardo.notesapp.data.database.RoomDataSource
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

/**
 * Integration tests
 * */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class SaveNoteUsecaseTest {

    @Test
    fun `invoke adds record to database`() {
        val dao = getInMemoryDatabase().noteDao()
        val usecase = SaveNoteUsecase(NoteRepository(RoomDataSource(dao)))

        runBlocking {

            /* Verify db is empty */
            assertEquals(dao.notesCount(), 0)

            /* Run usecase */
            usecase.invoke(NoteMobile(0, "text1", true))

            /* Verify record is added */
            val records = dao.findById(1)
            assertEquals(records.size, 1)
            assertNotNull(records.find { it.text == "text1" })

            /* Run usecase (add another note) */
            usecase.invoke(NoteMobile(0, "text2", false))

            /* Verify second record is added */
            val records2 = dao.findById(2)
            assertEquals(records2.size, 1)
            assertNotNull(records2.find { it.text == "text2" })

            /* Verify first record is still in db */
            val records3 = dao.findById(1)
            assertEquals(records3.size, 1)
            assertNotNull(records3.find { it.text == "text1" })

        }
    }
}