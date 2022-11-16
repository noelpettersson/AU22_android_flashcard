package com.example.au22_flashcard

import android.provider.UserDictionary.Words
import android.util.Log
import com.example.au22_flashcard.database.Word
import com.example.au22_flashcard.database.WordViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Observer

class WordList(viewModel: WordViewModel) {
    private var wordList = mutableListOf<Word>()
    private val usedWords = mutableListOf<Word>()

    var mViewModel = viewModel


    init {
        initializeWords()
    }


    fun initializeWords() {

    }


//    fun getNewWord() : Word {
//        val rnd = (0 until wordList.size).random()
//        return wordList[rnd]
//    }


    // alternativ 3
//    fun getNewWord() : Word {
//        if(wordList.isEmpty() ) {
//            initializeWords()
//        }
//
//        val rnd = (0 until wordList.size).random()
//        val word = wordList.removeAt(rnd)
//
//        return word
//    }

    //alternativ 1
    fun getNewWord() : Word {
        if (wordList.size == usedWords.size) {
            usedWords.clear()
        }

        var word : Word? = null

        do {
            val rnd = (0 until wordList.size).random()
            word = wordList[rnd]
        } while(usedWords.contains(word))

        usedWords.add(word!!)

        return word
    }

    // 1. en till lista med redan använda ord
    // 2. lista med index på använda ord
    // 3. använt ord tas bort från listan
    // 4. ordet håller reda på om det redan är använt

}








