package com.cygest.easmobile.ui.warehouse

import com.cygest.easmobile.libs.HttpBuilder
import com.cygest.easmobile.libs.WebService

/**
 * Repository class for Warehouse operations.
 */
class WarehouseRepository {
    /**
     * Get warehouses from API.
     * @return ListOf([Warehouse])
     */
    suspend fun getWarehouses(id: Int): List<Warehouse> {
        return HttpBuilder().build(WebService::class.java).getWareHouses(id)
    }
}
