package com.cygest.easmobile.ui.warehouse

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cygest.easmobile.CacheMemory
import com.cygest.easmobile.R
import com.cygest.easmobile.User

data class Warehouse(val Id: Int, val Name: String)

class WarehouseFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_warehouse, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listWarehouses: RecyclerView = view.findViewById(R.id.list_warehouses)
        listWarehouses.layoutManager = LinearLayoutManager(activity)
        listWarehouses.adapter = WarehouseAdapter(listOf())

        val user: User = CacheMemory.getUser(context)

        val warehouseViewModel: WarehouseViewModel =
                ViewModelProvider(this).get(WarehouseViewModel::class.java)
        warehouseViewModel.userId = user.Id

        try {
            warehouseViewModel.warehouses.observe(viewLifecycleOwner, Observer { warehouses ->
                listWarehouses.adapter = WarehouseAdapter(warehouses)
            })
        }
        catch (t: Throwable) {
            Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_LONG).show()
        }
    }
}