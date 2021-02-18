package com.diegofajardo.notesapp.data.database

import android.os.Build
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.diegofajardo.notesapp.domain.NoteMobile
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class RoomDataSourceTest : TestCase() {

    @Test
    fun testIsEmpty() {
        val roomDataSource = RoomDataSource(getInMemoryDatabase().noteDao())
        runBlocking {

            /* Verify db is empty */
            assertTrue(roomDataSource.isEmpty())

            /* Add a note */
            roomDataSource.saveNote(NoteMobile(0, "test", true))

            /* Verify not empty */
            assertFalse(roomDataSource.isEmpty())
        }
    }

    @Test
    fun testGetAll() {
        val roomDataSource = RoomDataSource(getInMemoryDatabase().noteDao())
        runBlocking {

            /* Verify db is empty */
            assertTrue(roomDataSource.getAll().isEmpty())

            /* Add a note */
            roomDataSource.saveNote(NoteMobile(0, "test0", true))

            /* Add a note */
            roomDataSource.saveNote(NoteMobile(0, "test1", true))

            /* Verify 2 records */
            val records = roomDataSource.getAll()
            assertEquals(records.size, 2)
            assertEquals(records.component1().id, 1)
            assertEquals(records.component1().text, "test0")
            assertEquals(records.component2().id, 2)
            assertEquals(records.component2().text, "test1")
        }
    }

    @Test
    fun testFindById() {
        val roomDataSource = RoomDataSource(getInMemoryDatabase().noteDao())
        runBlocking {

            /* Verify db is empty */
            assertTrue(roomDataSource.isEmpty())

            /* Add a note */
            roomDataSource.saveNote(NoteMobile(0, "test", true))

            /* Add a note */
            roomDataSource.saveNote(NoteMobile(1, "test", true))

            /* Verify 1 record with id */
            val records = roomDataSource.findById(1)
            assertEquals(records.size, 1)
            assertEquals(records.component1().id, 1)
        }
    }

    @Test
    fun testCountAll() {
        val roomDataSource = RoomDataSource(getInMemoryDatabase().noteDao())
        runBlocking {

            /* Verify db is empty */
            assertTrue(roomDataSource.isEmpty())

            /* Add a note */
            roomDataSource.saveNote(NoteMobile(0, "test0", true))

            /* Verify count is equal to 1 */
            assertEquals(roomDataSource.countAll(), 1)

            /* Add a note */
            roomDataSource.saveNote(NoteMobile(0, "test1", true))

            /* Verify count is equal to 2 */
            assertEquals(roomDataSource.countAll(), 2)
        }
    }

    @Test
    fun testSaveNote() {
        val roomDataSource = RoomDataSource(getInMemoryDatabase().noteDao())
        runBlocking {

            /* Verify db is empty */
            assertTrue(roomDataSource.isEmpty())

            /* Add a note */
            roomDataSource.saveNote(NoteMobile(0, "test", true))

            /* Verify record is saved */
            val records = roomDataSource.findById(1)
            assertEquals(records.size, 1)
            assertEquals(records.component1().id, 1)

            /* Add a note */
            roomDataSource.saveNote(NoteMobile(0, "test", true))

            /* Verify record is saved */
            val records2 = roomDataSource.findById(2)
            assertEquals(records2.size, 1)
            assertEquals(records2.component1().id, 2)
        }
    }

    @Test
    fun testUpdateNote() {
        val roomDataSource = RoomDataSource(getInMemoryDatabase().noteDao())
        runBlocking {

            /* Verify db is empty */
            assertTrue(roomDataSource.isEmpty())

            /* Add a note */
            roomDataSource.saveNote(NoteMobile(0, "test0", true))

            /* Verify record is saved */
            val records = roomDataSource.findById(1)
            assertEquals(records.size, 1)
            assertEquals(records.component1().id, 1)
            assertEquals(records.component1().text, "test0")

            /* Update the note */
            roomDataSource.updateNote(NoteMobile(1, "testUpdated", false))

            /* Verify record is updated */
            val records2 = roomDataSource.findById(1)
            assertEquals(records2.size, 1)
            assertEquals(records2.component1().id, 1)
            assertEquals(records2.component1().text, "testUpdated")
            assertEquals(records2.component1().isFavourite, false)
        }
    }

    @Test
    fun testDeleteNote() {
        val roomDataSource = RoomDataSource(getInMemoryDatabase().noteDao())
        runBlocking {

            /* Verify db is empty */
            assertTrue(roomDataSource.isEmpty())

            /* Add a note */
            roomDataSource.saveNote(NoteMobile(0, "test", true))

            /* Verify record is saved */
            val records = roomDataSource.findById(1)
            assertEquals(records.size, 1)
            assertEquals(records.component1().id, 1)

            /* Delete the note */
            roomDataSource.deleteNote(1)

            /* Verify no records */
            val records2 = roomDataSource.findById(0)
            assertTrue(records2.isEmpty())
        }
    }

    private fun getInMemoryDatabase(): NoteDatabase {
        return Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            NoteDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
    }
}