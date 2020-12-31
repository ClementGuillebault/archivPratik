package com.cygest.easmobile.ui.warehouse

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WarehouseViewModel(): ViewModel() {
    // private val _warehouses = MutableLiveData<List<Warehouse>>()
    // val warehouses2: LiveData<List<Warehouse>> = _warehouses

    val warehouses: (Int) -> LiveData<List<Warehouse>> = {
        id: Int -> liveData(Dispatchers.IO) {
            emit(WarehouseRepository().getWarehouses(id))
        }
    }

//    val warehouses2: (Int) -> Job = {
//        id: Int -> viewModelScope.launch {
//            _warehouses.value = WarehouseRepository().getWarehouses(id)
//        }
//    }
//
//    fun getWarehouse(id: Int) {
//        viewModelScope.launch {
//            _warehouses.value = WarehouseRepository().getWarehouses(id)
//        }
//    }
}