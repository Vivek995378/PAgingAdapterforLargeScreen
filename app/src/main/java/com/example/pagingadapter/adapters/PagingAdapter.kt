package com.example.pagingadapter.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pagingadapter.R
import com.example.pagingadapter.models.CharacterData

class PagingAdapter(): PagingDataAdapter<CharacterData, PagingAdapter.MyViewHolder>(DiffUtilCallBack()) {

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val imageView: ImageView = view.findViewById(R.id.imageView)
        val tvName: TextView = view.findViewById(R.id.tvName)

        fun bind(data: CharacterData) {
            tvName.text = data.name

            Glide.with(imageView)
                .load(data.image)
                .circleCrop()
                .into(imageView)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class DiffUtilCallBack: DiffUtil.ItemCallback<CharacterData>() {
        override fun areItemsTheSame(oldItem: CharacterData, newItem: CharacterData): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: CharacterData, newItem: CharacterData): Boolean {
            return oldItem.name == newItem.name
                    && oldItem.species == newItem.species
        }
    }

}