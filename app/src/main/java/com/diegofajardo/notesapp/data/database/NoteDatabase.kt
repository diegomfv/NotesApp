package com.diegofajardo.notesapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.diegofajardo.notesapp.data.database.model.NoteDb

@Database(entities = [NoteDb::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    companion object {

        fun build(context: Context) = Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            "note-database"
        ).build()

    }

    abstract fun noteDao(): NoteDao

}