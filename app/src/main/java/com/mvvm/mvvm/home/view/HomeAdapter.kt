package com.mvvm.mvvm.home.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mvvm.mvvm.databinding.ItemHomeAdapterBinding
import com.mvvm.mvvm.home.model.MovieItem

class MovieAdapter : RecyclerView.Adapter<MainViewHolder>() {
    var MovieItems = mutableListOf<MovieItem>()

    fun setMovieItemList(MovieItems: List<MovieItem>) {
        this.MovieItems = MovieItems.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHomeAdapterBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val MovieItem = MovieItems[position]
        holder.binding.tvOptionTitle.text = MovieItem.name
        Glide.with(holder.itemView.context).load(MovieItem.imageUrl).into(holder.binding.ivOption)
    }

    override fun getItemCount(): Int {
        return MovieItems.size
    }
}

class MainViewHolder(val binding: ItemHomeAdapterBinding) : RecyclerView.ViewHolder(binding.root)