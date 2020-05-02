package com.example.note

import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface NoteDao {

    // LiveData is a data holder class that can be observed within a given lifecycle.
    // Always holds/caches latest version of data. Notifies its active observers when the
    // data has changed. Since we are getting all the contents of the database,
    // we are notified whenever any of the database contents have changed.
    @Query("SELECT * from word_table ORDER BY word ASC")
    fun getAlphabetizedWords(): LiveData<List<Word>>

//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun insert(word: Word)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: Word?)

    @Delete
    fun delete(note: Word?)

    @Update
    fun update(note: Word?)

    @Query("DELETE FROM word_table")
     fun deleteAll()
}