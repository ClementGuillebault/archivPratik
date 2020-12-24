package com.cygest.easmobile.ui.warehouse

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cygest.easmobile.CacheMemory
import com.cygest.easmobile.R
import com.cygest.easmobile.User
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

data class Warehouse(val Id: Int, val Name: String)

@AndroidEntryPoint
class WarehouseFragment : Fragment() {

    @Inject
    lateinit var warehouseViewModel: WarehouseViewModel


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_warehouse, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var listWarehouses: RecyclerView = view.findViewById(R.id.list_warehouses)
        listWarehouses.layoutManager = LinearLayoutManager(activity)

        warehouseViewModel.warehouses.observe(viewLifecycleOwner, Observer {
            warehouses ->
            listWarehouses.layoutManager = LinearLayoutManager(activity)
            val tmp = WarehouseAdapter(warehouses)
            tmp.onItemClick = { warehouse ->
                var user = CacheMemory.getUser(Activity())
                user.warehouse = warehouse
                Navigation.findNavController(view).navigate(R.id.nav_host_fragment)
            }
            listWarehouses.adapter = WarehouseAdapter(warehouses)
        })
    }
}