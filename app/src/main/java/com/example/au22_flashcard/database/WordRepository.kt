package com.example.au22_flashcard.database

import androidx.lifecycle.LiveData

//Repository for room
class WordRepository(private val wordDao: WordDao) {

    val allWords: LiveData<List<Word>> = wordDao.getAll()

    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }

    suspend fun delete(id: Int) {
        wordDao.delete(id)
    }

    suspend fun deleteAll() {
        wordDao.deleteAll()
    }
}