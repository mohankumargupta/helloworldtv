package com.melbpc.mohankumargupta.helloworldtv

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel(private val state: SavedStateHandle): ViewModel() {
    private val _collectionDay = MutableStateFlow("Monday")
    val collectionDay = _collectionDay.asStateFlow()

    fun setCollectionDay(day: String)  {
        _collectionDay.value = day
    }
}