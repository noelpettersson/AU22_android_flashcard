package com.example.au22_flashcard.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.au22_flashcard.database.Word

@Dao
interface WordDao {

    @Insert
    suspend fun insert(word: Word)

    // delete specific word
    @Query("DELETE FROM word_table WHERE id = :id")
    suspend fun delete(id: Int)

    // getAllwords
    @Query("SELECT * FROM word_table")
    fun getAll(): LiveData<List<Word>>

    //Delete all words
    @Query("DELETE FROM word_table")
    suspend fun deleteAll()

}