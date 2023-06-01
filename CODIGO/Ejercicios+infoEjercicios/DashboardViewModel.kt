package com.example.eztrain.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Esto va a ser el fragmento de ejercicios"
    }
    val text: LiveData<String> = _text
}