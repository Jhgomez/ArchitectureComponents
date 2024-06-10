package com.example.dessertclicker

import com.example.dessertclicker.model.Dessert

data class DessertUiState (
    val desserts: List<Dessert> = emptyList(),
    val revenue: Int = 0,
    val dessertsSold: Int = 0
)