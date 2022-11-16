package com.example.au22_flashcard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global
import android.util.Log
import android.view.MotionEvent
import android.widget.TextView
import com.example.au22_flashcard.database.Word
import com.example.au22_flashcard.database.WordViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var wordView : TextView
    var currentWord : Word? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mWordViewModel = WordViewModel(application)

        //put words in a list




        //val wordList = WordList(mWordViewModel)

        //Go to addWord activity if button is pressed
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

        //showNewWord()

        wordView.setOnClickListener {
            revealTranslation()
        }

    }

    fun revealTranslation() {
       // wordView.text = currentWord?.english
    }


    fun showNewWord() {

       // currentWord = wordList.getNewWord()
       // wordView.text = currentWord?.swedish
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











