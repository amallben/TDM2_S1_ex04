package com.example.note

import android.R.id
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


//import androidx.test.orchestrator.junit.BundleJUnitUtils.getDescription


class MainActivity : AppCompatActivity() {

    private val newWordActivityRequestCode = 1
    private val EDIT_NOTE_REQUEST = 2
    private lateinit var wordViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = NoteListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Get a new or existing ViewModel from the ViewModelProvider.
        wordViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(this.application)
        )[NoteViewModel::class.java]
        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        wordViewModel.allWords.observe(this, Observer { words ->
            // Update the cached copy of the words in the adapter.
            words?.let { adapter.setWords(it) }
        })

        val fab = findViewById<LinearLayout>(R.id.new_note)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewWordActivity::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                wordViewModel.delete(adapter.getNoteAt(viewHolder.adapterPosition))
                Toast.makeText(this@MainActivity, "Note deleted", Toast.LENGTH_SHORT).show()
            }
        }).attachToRecyclerView(recyclerView)

//        adapter.setOnItemClickListener(object : NoteListAdapter.OnItemClickListener {
//
//
//            override fun onItemClick(note: Word) {
//                val intent = Intent(this@MainActivity, NewWordActivity::class.java)
//                intent.putExtra(NewWordActivity.EXTRA_REPLY, note.word)
//                intent.putExtra(NewWordActivity.EXTRA_DESCRIPTION, note.description)
//                intent.putExtra(NewWordActivity.EXTRA_DATE, note.date)
//                intent.putExtra(NewWordActivity.EXTRA_COLOR, note.color)
//                startActivityForResult(intent, EDIT_NOTE_REQUEST)  }
//        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            val description: String? =
                intentData?.getStringExtra(NewWordActivity.EXTRA_DESCRIPTION)
            val titre: String? =intentData?.getStringExtra(NewWordActivity.EXTRA_REPLY)
            val date: String? =intentData?.getStringExtra(NewWordActivity.EXTRA_DATE)
            val color: String? =intentData?.getStringExtra(NewWordActivity.EXTRA_COLOR)
            println(color.toString().toInt())
                val word = Word(titre.toString(),description.toString(),date.toString(),color.toString().toInt())
                wordViewModel.insert(word)
                Unit

        }
        else
            if (requestCode == EDIT_NOTE_REQUEST && resultCode == Activity.RESULT_OK)
            {
                val description: String? =
                    intentData?.getStringExtra(NewWordActivity.EXTRA_DESCRIPTION)
                val titre: String? =intentData?.getStringExtra(NewWordActivity.EXTRA_REPLY)
                val date: String? =intentData?.getStringExtra(NewWordActivity.EXTRA_DATE)
                val color: String? =intentData?.getStringExtra(NewWordActivity.EXTRA_COLOR)

                val word = Word(titre.toString(),description.toString(),date.toString(),color.toString().toInt())

                wordViewModel.update(word)

                Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show()

            }
            else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
