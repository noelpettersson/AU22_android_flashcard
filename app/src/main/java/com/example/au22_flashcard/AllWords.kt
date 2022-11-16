package com.example.au22_flashcard

import WordListAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.au22_flashcard.database.WordViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AllWords : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_words)

        val mWordViewModel = WordViewModel(application)

        // Recyclerview
        val adapter = WordListAdapter(mWordViewModel)
        val recyclerView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)

        // Submit all words to recyclerview from the viewmodel
        mWordViewModel.allWords.observe(this) { word ->
            Log.d("listWords", "onCreate: $word")
            adapter.submitList(word)
        }

        //Delete all words if floating action button is pressed
        findViewById<FloatingActionButton>(R.id.deleteAll).setOnClickListener {
            mWordViewModel.deleteAll()
        }

        //Go back to main if floating action button is pressed
        findViewById<FloatingActionButton>(R.id.goBack).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

}