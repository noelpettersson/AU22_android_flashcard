package com.example.au22_flashcard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.insert
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.au22_flashcard.database.AppDatabase
import com.example.au22_flashcard.database.Word
import com.example.au22_flashcard.database.WordViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class AddWords : AppCompatActivity() {
    lateinit var mViewModel: WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_words)

        // Get a new or existing ViewModel from the ViewModelProvider
        mViewModel = ViewModelProvider(this)[WordViewModel::class.java]

        findViewById<Button>(R.id.confirm).setOnClickListener() {
            insertDataToDatabase()
        }

        //If floating action button is pressed go back to main
        findViewById<FloatingActionButton>(R.id.backToMain).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    // Function to insert words to database
    private fun insertDataToDatabase() {
        val english = findViewById<android.widget.EditText>(R.id.englishInput)?.text.toString()
        val swedish = findViewById<android.widget.EditText>(R.id.swedishInput)?.text.toString()

        //If input is not empty insert to database
        if (inputCheck(english, swedish)) {
            // Create Word Object
            val word = Word(0, english, swedish)
            // Add Data to Database
            mViewModel.insert(word)
            Toast.makeText(this, "Successfully added!", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }

    // Function to check if input is empty
    private fun inputCheck(english: String, swedish: String): Boolean {
        return !(english.isEmpty() && swedish.isEmpty())
    }
}