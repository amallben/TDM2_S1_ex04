package com.example.note

import android.os.AsyncTask
import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.lifecycle.LiveData


class NoteRepository(private val wordDao: NoteDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allWords: LiveData<List<Word>> = wordDao.getAlphabetizedWords()

    suspend fun insert(word: Word) {
        //wordDao.insert(word)
        InsertNoteAsyncTask(wordDao).execute(word)


    }
    private class InsertNoteAsyncTask(private val noteDao: NoteDao?) :
        AsyncTask<Word?, Void?, Void?>() {


        protected override fun doInBackground(vararg params: Word?): Void? {
            noteDao!!.insert(params[0])
            return null   }

    }

    fun delete(word: Word) {
        DeleteNoteAsyncTask(wordDao).execute(word)
    }

    private class DeleteNoteAsyncTask(private val noteDao: NoteDao) :
        AsyncTask<Word?, Void?, Void?>() {


        override fun doInBackground(vararg params: Word?): Void? {
            noteDao.delete(params[0])
            return null   }

    }

    fun update(note: Word) {
        UpdateNoteAsyncTask(wordDao).execute(note)
    }
    private class UpdateNoteAsyncTask(private val noteDao: NoteDao) :
        AsyncTask<Word, Void?, Void?>() {


        override fun doInBackground(vararg params: Word): Void? {
            noteDao.update(params[0])
            return null }

    }


}