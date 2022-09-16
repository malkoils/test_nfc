package com.example.test_nfc.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class settingsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Settings Fragment"
    }
    val text: LiveData<String> = _text
}