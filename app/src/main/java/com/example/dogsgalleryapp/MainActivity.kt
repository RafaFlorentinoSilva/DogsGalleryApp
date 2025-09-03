// MainActivity.kt
package com.example.dogsgalleryapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dogsgalleryapp.api.DogApiService
import com.example.dogsgalleryapp.repository.DogRepository
import com.example.dogsgalleryapp.view.DogAdapter
import com.example.dogsgalleryapp.viewmodel.DogViewModel
import com.example.dogsgalleryapp.viewmodel.DogViewModelFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.dogsgalleryapp.BuildConfig
import com.example.dogsgalleryapp.R // Importe a classe R para acessar recursos do layout

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: DogViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DogAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(DogApiService::class.java)
        val repository = DogRepository(apiService, BuildConfig.THE_DOG_API_KEY)

        val viewModelFactory = DogViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[DogViewModel::class.java]

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        adapter = DogAdapter(emptyList())
        recyclerView.adapter = adapter

        viewModel.dogImages.observe(this) { dogs ->
            adapter.updateDogs(dogs)
        }
    }
}