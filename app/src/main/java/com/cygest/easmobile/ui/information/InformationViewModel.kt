package com.cygest.easmobile.ui.information

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData

class InformationViewModel : ViewModel() {
    // val unit: LiveData<Unit>
    var cb: LiveData<String> = MutableLiveData<String>()
}