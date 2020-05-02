package com.example.note



import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class NoteListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<NoteListAdapter.WordViewHolder>() {


    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var words = emptyList<Word>() // Cached copy of words
    private var listenerr: OnItemClickListener? = null

    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordItemView: TextView = itemView.findViewById(R.id.textView)
        var textViewDescription :TextView = itemView.findViewById(R.id.text_view_description)

        fun WordViewHolder(itemView: View) {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (listenerr != null && position != RecyclerView.NO_POSITION) {
                    listenerr!!.onItemClick(words.get(position))
                }
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return WordViewHolder(itemView)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = words[position]
        holder.wordItemView.text = current.word +"          "+ current.date
        holder.textViewDescription.text=current.description
        holder.wordItemView.setBackgroundColor(current.color)
    }

    fun getNoteAt(position: Int): Word {
        return words.get(position)
    }

    internal fun setWords(words: List<Word>) {
        this.words = words
        notifyDataSetChanged()
    }

    override fun getItemCount() = words.size
    interface OnItemClickListener {
        fun onItemClick(note: Word)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
       listenerr = listener
    }
}