package com.example.dogsgalleryapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.dogsgalleryapp.R
import com.example.dogsgalleryapp.model.DogResponse

class DogAdapter(private var dogImages: List<DogResponse>) : RecyclerView.Adapter<DogAdapter.DogViewHolder>() {

    class DogViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dogImage: ImageView = view.findViewById(R.id.dogImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dog, parent, false)
        return DogViewHolder(view)
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.dogImage.load(dogImages[position].url)
    }

    override fun getItemCount() = dogImages.size

        fun updateDogs(newDogs: List<DogResponse>) {
        dogImages = newDogs
        notifyDataSetChanged()
    }
}