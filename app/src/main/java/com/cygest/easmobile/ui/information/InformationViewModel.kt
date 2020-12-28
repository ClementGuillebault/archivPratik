package com.cygest.easmobile.ui.information

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData

class InformationViewModel : ViewModel() {
    var cb: String = ""
    val informations: LiveData<Any> = liveData { emit(InformationRepository().getInformation("010000009404241")) }
}