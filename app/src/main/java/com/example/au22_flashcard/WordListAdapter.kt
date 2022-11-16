import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.au22_flashcard.*
import com.example.au22_flashcard.database.Word
import com.example.au22_flashcard.database.WordViewModel

class WordListAdapter(val viewModel: WordViewModel) : ListAdapter<Word, WordListAdapter.WordViewHolder>(WordsComparator()) {
    //OnCreateViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder.create(parent)
    }

    //onBindViewHolder() is called by RecyclerView to display the data at the specified position.
    // This method should update the contents of the itemView to reflect the item at the given position.

    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //variables for english and swedish word
        private val englishItemView: TextView = itemView.findViewById(R.id.english)
        private val swedishItemView: TextView = itemView.findViewById(R.id.swedish)


        //Bind
        fun bind(english: String?, swedish: String?) {
            englishItemView.text = "Engelska: $english"
            swedishItemView.text = "Svenska: $swedish"
        }


        companion object {
            fun create(parent: ViewGroup): WordViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return WordViewHolder(view)
            }
        }
    }

    class WordsComparator : DiffUtil.ItemCallback<Word>() {
        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem === newItem
        }

        //are contents the same for english word and swedish word
        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem.english == newItem.english && oldItem.swedish == newItem.swedish
        }

    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.english, current.swedish)
        //Delete button
        val deleteButton: Button = holder.itemView.findViewById(R.id.delete)
        deleteButton.setOnClickListener {
            viewModel.delete(current.id)
        }
    }

}