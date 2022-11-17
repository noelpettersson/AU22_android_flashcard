package com.example.au22_flashcard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global
import android.util.Log
import android.view.MotionEvent
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.au22_flashcard.database.Word
import com.example.au22_flashcard.database.WordViewModel
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    lateinit var mWordViewModel: WordViewModel
    lateinit var wordView : TextView
    var currentWord : Word? = null
    var usedWords : MutableList<String> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mWordViewModel = ViewModelProvider(this)[WordViewModel::class.java]

        findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.addWordButton).setOnClickListener {
            val intent = Intent(this, AddWords::class.java)
            startActivity(intent)
        }

        //Go to listWords activity if button is pressed
        findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.listWordsButton).setOnClickListener {
            val intent = Intent(this, AllWords::class.java)
            startActivity(intent)
        }

        wordView = findViewById(R.id.wordTextView)
        getWord() //Get a word from the database




        //showNewWord()

        wordView.setOnClickListener {
            revealTranslation()
        }

    }

    fun revealTranslation() {
        wordView.text = currentWord?.swedish
    }

    fun getWord() {
        //use getTableSize from viewmodel to check if all words have been used
        mWordViewModel.getTableSize().observe(this) { size ->
            if (size == usedWords.size) {
                usedWords.clear()
                getWord()
            } else {
                //Get random word from viewmodel and use observer to look at live data
                mWordViewModel.getRandomWord().observe(this) { word ->
                    if (word != null && !usedWords.contains(word.english)) {
                        currentWord = word
                        wordView.text = word.english
                        usedWords.add(word.english)
                    } // else if all words have been used, reset usedWords and get a new word
                    else if(word != null && usedWords.contains(word.english)) {
                        getWord()
                    }

                    else if(word == null) {
                        wordView.text = "No words in database"
                    }
                }
            }
            }
        }

    fun showNewWord() {
        getWord()
        wordView.text = currentWord?.english
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {

        if (event?.action == MotionEvent.ACTION_UP) {
            showNewWord()
        }

        return true
    }
}


//Vad ska göras:

//1. skapa en ny aktivitet där ett nytt ord får skrivas in
//2. spara det nya ordet i databasen.

//3. I main activity läs in alla ord från databasen

// (anväd coroutiner när ni läser och skriver till databasen se tidigare exempel)











