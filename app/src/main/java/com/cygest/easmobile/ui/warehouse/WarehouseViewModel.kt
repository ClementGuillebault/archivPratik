package com.cygest.easmobile.ui.warehouse

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData

class WarehouseViewModel(): ViewModel() {
    var userId: Int = 0

    val warehouses: LiveData<List<Warehouse>> = liveData { emit(WarehouseRepository().getWarehouses(userId)) }
}