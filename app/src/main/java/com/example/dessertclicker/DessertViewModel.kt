package com.example.dessertclicker

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource
import com.example.dessertclicker.model.Dessert
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
            val dessertToShow = determineDessertToShow(dessertsSold)

            currentStatus.copy(
                dessertsSold = dessertsSold,
                currentDessertPrice = dessertToShow.price,
                currentDessertImageId = dessertToShow.imageId,
                revenue = revenue
            )
        }
    }


    /**
     * Determine which dessert to show.
     */
    private fun determineDessertToShow(
        dessertsSold: Int
    ): Dessert {
        var dessertToShow = _uiState.value.desserts.first()
        for (dessert in uiState.value.desserts) {
            if (dessertsSold >= dessert.startProductionAmount) {
                dessertToShow = dessert
            } else {
                // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
                // you'll start producing more expensive desserts as determined by startProductionAmount
                // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
                // than the amount sold.
                break
            }
        }

        return dessertToShow
    }
}