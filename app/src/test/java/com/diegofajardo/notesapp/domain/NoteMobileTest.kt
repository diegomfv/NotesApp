package com.diegofajardo.notesapp.domain

import org.junit.Assert
import org.junit.Test

class NoteMobileTest {

    @Test
    fun constructorTest() {
        val note = NoteMobile(0, "test", true)
        Assert.assertEquals(note.id, 0)
        Assert.assertEquals(note.text, "test")
        Assert.assertEquals(note.isFavourite, true)
    }


}