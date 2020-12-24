package com.cygest.easmobile.ui.warehouse

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class WarehouseViewModel @Inject constructor(
    warehouseRepository: WarehouseRepository
) : ViewModel() {

    val warehouses: LiveData<List<Warehouse>> = warehouseRepository.getWarehouses()
}