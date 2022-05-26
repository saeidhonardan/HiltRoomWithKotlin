package ir.rasadev.video.hiltdaggerproject.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ir.rasadev.video.hiltdaggerproject.databinding.ItemRecyclereBinding
import ir.rasadev.video.hiltdaggerproject.room.NoteModel
import javax.inject.Inject


class NoteAdapter @Inject constructor() : RecyclerView.Adapter<NoteAdapter.NoteHolder>() {
    lateinit var binding: ItemRecyclereBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.NoteHolder {
        binding = ItemRecyclereBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteHolder()
    }

    override fun onBindViewHolder(holder: NoteAdapter.NoteHolder, position: Int) {
        holder.getData(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class NoteHolder : RecyclerView.ViewHolder(binding.root) {
        fun getData(model: NoteModel) {
            binding.apply {
                txtItem.text = "${model.id} : ${model.title}"
                root.setOnClickListener(View.OnClickListener {
                    onItmClickListener?.let {
                        it(model)
                    }
                })
            }
        }
    }

    //for no repeat data
    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    //click item that act in activity
private var onItmClickListener : ((NoteModel)-> Unit) ?= null
    fun setOnItemClickListener(listener:(NoteModel)-> Unit){
        onItmClickListener = listener
    }
    //end

    val callbackDiffer = object : DiffUtil.ItemCallback<NoteModel>() {
        override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, callbackDiffer)
}