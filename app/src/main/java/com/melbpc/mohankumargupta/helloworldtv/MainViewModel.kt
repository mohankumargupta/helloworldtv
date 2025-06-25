package com.melbpc.mohankumargupta.helloworldtv

import androidx.compose.ui.graphics.Color
//import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate


class MainViewModel(): ViewModel() {
    private val _collectionDay = MutableStateFlow("Monday")
    val collectionDay = _collectionDay.asStateFlow()

    private val _recyclingReferenceDate = MutableStateFlow(LocalDate.MIN)
    val recyclingReferenceDate = _recyclingReferenceDate.asStateFlow()

    private val _recyclingBinColor = MutableStateFlow(ColorSwatch.Black)
    val recyclingBinColor = _recyclingBinColor.asStateFlow()

    private val _gardenBinColor = MutableStateFlow(ColorSwatch.Black)
    val gardenBinColor = _gardenBinColor.asStateFlow()

    fun setCollectionDay(day: String)  {
        _collectionDay.value = day
    }

    fun setRecyclingReferenceDate(date: LocalDate) {
        _recyclingReferenceDate.value = date
    }

    fun setRecyclingBinColor(color: ColorSwatch) {
        _recyclingBinColor.value = color
    }

    fun setGardenBinColor(color: ColorSwatch) {
       _gardenBinColor.value = color
    }

}