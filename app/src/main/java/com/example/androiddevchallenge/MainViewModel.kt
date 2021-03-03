/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
