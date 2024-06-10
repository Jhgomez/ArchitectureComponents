package com.example.dessertclicker

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(DessertUiState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.value = DessertUiState(
            desserts = Datasource.dessertList,
            currentDessertImageId = Datasource.dessertList[0].imageId,
            currentDessertPrice = Datasource.dessertList[0].price
        )
    }

    fun addItemToCart() {
        _uiState.update { currentStatus ->
            val revenue = currentStatus.revenue + currentStatus.currentDessertPrice
            val dessertsSold = currentStatus.dessertsSold.inc()
            // Show the next dessert
            val dessertToShow = determineDessertToShow(currentStatus.desserts, dessertsSold)

            currentStatus.copy(
                dessertsSold = dessertsSold,
                currentDessertPrice = dessertToShow.price,
                currentDessertImageId = dessertToShow.imageId,
                revenue = revenue
            )
        }
    }
}