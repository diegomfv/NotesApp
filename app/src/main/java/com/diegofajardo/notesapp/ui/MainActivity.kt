package com.diegofajardo.notesapp.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.diegofajardo.notesapp.R
import com.diegofajardo.notesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var view: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { view = it.root }
        setContentView(R.layout.activity_main)
        subscribeToModel()
    }

    private fun subscribeToModel () {
        TODO("Not implemented")
    }

}

