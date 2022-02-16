package com.taha.artbooknavigation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.taha.artbooknavigation.databinding.ArtRowBinding
import com.taha.artbooknavigation.model.Art
import com.taha.artbooknavigation.view.ArtsDirections

class ArtsAdapter(val artList : List<Art>) : RecyclerView.Adapter<ArtsAdapter.ArtHolder>() {

    class ArtHolder(val binding: ArtRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtHolder {
        val binding = ArtRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArtHolder(binding)
    }

    override fun onBindViewHolder(holder: ArtHolder, position: Int) {
        holder.binding.artNameText.text = artList[position].artName
        holder.itemView.setOnClickListener {
            val action = ArtsDirections.actionArtsToArtUpload(artList[position].id, "old")
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return artList.size
    }
}