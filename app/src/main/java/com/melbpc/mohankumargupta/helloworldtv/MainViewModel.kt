package com.melbpc.mohankumargupta.helloworldtv

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate


class MainViewModel(private val state: SavedStateHandle): ViewModel() {
    private val _collectionDay = MutableStateFlow("Monday")
    val collectionDay = _collectionDay.asStateFlow()

    private val _recyclingReferenceDate = MutableStateFlow(LocalDate.MIN)
    val recyclingReferenceDate = _recyclingReferenceDate.asStateFlow()

    fun setCollectionDay(day: String)  {
        _collectionDay.value = day
    }

    fun setRecyclingReferenceDate(date: LocalDate) {
        _recyclingReferenceDate.value = date
    }

}