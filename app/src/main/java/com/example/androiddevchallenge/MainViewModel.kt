package com.example.androiddevchallenge

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.model.Dog
import com.example.androiddevchallenge.model.getPets
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    /**
     * The LiveData variable to observe Contact object list.
     */
    val dogsLiveData: LiveData<List<Dog>>
        get() = _dogsLiveData

    private val _dogsLiveData = MutableLiveData<List<Dog>>()

    private val dogs = mutableListOf<Dog>()

    private val handler = CoroutineExceptionHandler { _, e ->
        e.printStackTrace()
    }

    fun readData() = viewModelScope.launch(handler) {
        _dogsLiveData.value = getPets()
    }

}