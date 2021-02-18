package com.diegofajardo.notesapp.domain

/**
 * Note domain model
 * */
data class NoteMobile(
    val id: Int,
    val text: String,
    val isFavourite: Boolean
)
