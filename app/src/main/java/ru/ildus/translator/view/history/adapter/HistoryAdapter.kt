package ru.ildus.translator.view.history.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import ru.ildus.translator.databinding.ActivityHistoryRecyclerviewItemBinding
import ru.ildus.model.data.DataModel

class HistoryAdapter: RecyclerView.Adapter<HistoryAdapter.RecyclerItemViewHolder>() {
    private var data: List<DataModel> = arrayListOf()

    fun setData(data: List<DataModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ActivityHistoryRecyclerviewItemBinding.inflate(inflater)
        return RecyclerItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(val binding: ActivityHistoryRecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: DataModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                binding.headerHistoryTextviewRecyclerItem.text = data.text
       //         binding.descriptionTextviewRecyclerItem.text = data.meanings?.get(0)?.translation?.translation
           //     binding.clearTextImageview =
                itemView.setOnClickListener {
                    Toast.makeText(itemView.context, " ${data.text}", Toast.LENGTH_SHORT).show()
                }
  //              addOnClearClickListener()
            }
        }
    }
//    private fun addOnClearClickListener() {
//         var clearTextImageView: ImageView
//        clearTextImageView.setOnClickListener {
//
//        }
//    }
}
