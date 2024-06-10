package com.example.dessertclicker

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DessertViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(DessertUiState())
    val uiState = _uiState.asStateFlow()

//    private val currentDessertPrice =

    init {
        _uiState.value = DessertUiState(
            desserts = Datasource.dessertList,
            currentDessertImageId = Datasource.dessertList[0].imageId,
            currentDessertPrice = Datasource.dessertList[0].imageId

        )
    }

//    fun addToCart(int)
}