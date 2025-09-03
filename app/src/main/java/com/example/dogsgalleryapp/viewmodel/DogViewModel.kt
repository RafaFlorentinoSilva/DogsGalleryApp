// DogViewModel.kt
package com.example.dogsgalleryapp.viewmodel // Certifique-se de que o pacote está correto

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogsgalleryapp.model.DogResponse // Importe a classe DogResponse
import com.example.dogsgalleryapp.repository.DogRepository
import kotlinx.coroutines.launch

class DogViewModel(private val repository: DogRepository) : ViewModel() {

    // Altere de List<String> para List<DogResponse>
    private val _dogImages = MutableLiveData<List<DogResponse>>()
    val dogImages: LiveData<List<DogResponse>> get() = _dogImages

    init {
        fetchDogImages()
    }

    private fun fetchDogImages() {
        viewModelScope.launch {
            try {
                // A chamada ao repositório já retorna List<DogResponse>,
                // então a atribuição é direta e sem erros
                _dogImages.value = repository.getRandomDogs()
            } catch (e: Exception) {
                // Tratar erro aqui
                _dogImages.value = emptyList() // Opcional: define uma lista vazia em caso de erro
            }
        }
    }
}