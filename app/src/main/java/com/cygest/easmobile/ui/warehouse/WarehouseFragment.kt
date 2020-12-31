package com.cygest.easmobile.ui.warehouse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cygest.easmobile.CacheMemory
import com.cygest.easmobile.R
import com.cygest.easmobile.User

data class Warehouse(val Id: Int, val Name: String)

class WarehouseFragment : Fragment() {

    private val warehouseViewModel by viewModels<WarehouseViewModel>()
    private val listWarehouses by lazy { view?.findViewById<RecyclerView>(R.id.list_warehouses) }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_warehouse, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listWarehouses?.layoutManager = LinearLayoutManager(activity)
        listWarehouses?.adapter = WarehouseAdapter(listOf())

        val user: User = CacheMemory.getUser(context)

        warehouseViewModel.warehouses(user.Id).observe(viewLifecycleOwner) {
            listWarehouses?.adapter = WarehouseAdapter(it)
        }

/*      WITHOUT VIEWMODEL
//        CoroutineScope(Dispatchers.IO).launch {
//            delay(5000)
//            val t = WarehouseRepository().getWarehouses(user.Id)
//            withContext(Dispatchers.Main) {
//                listWarehouses.adapter = WarehouseAdapter(t)
//            }
//        }



//        warehouseViewModel.getWarehouse(user.Id)
//        warehouseViewModel.warehouses2.observe(viewLifecycleOwner) {
//            listWarehouses.adapter = WarehouseAdapter(it)
        }
*/
    }
}