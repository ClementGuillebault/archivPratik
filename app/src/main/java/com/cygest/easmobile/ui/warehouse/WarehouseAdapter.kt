package com.cygest.easmobile.ui.warehouse

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.cygest.easmobile.CacheMemory
import com.cygest.easmobile.R

class WarehouseAdapter(private val listOfWarehouse: List<Warehouse>) :
        RecyclerView.Adapter<WarehouseAdapter.WareHouseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WareHouseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return WareHouseViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: WareHouseViewHolder, position: Int) {
        val warehouse = listOfWarehouse[position]
        holder.bind(warehouse)
        holder.itemView.setOnClickListener {
            val user = CacheMemory.getUser(it.context)
            user.Repository = warehouse.Id
            CacheMemory.save(it.context, user)
            // var precedentId = holder.itemView.findNavController().currentDestination?.parent?.startDestination
            holder.itemView.findNavController().navigate(R.id.nav_home)
        }
    }

    override fun getItemCount(): Int = listOfWarehouse.size

    inner class WareHouseViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.warehouse_list_item, parent, false)) {
        private var mTitleView: TextView? = null
        private var mImg: ImageView? = null

        init {
            mTitleView = itemView.findViewById(R.id.list_warehouses_title)
        }

        fun bind(warehouse: Warehouse) {
            mTitleView?.text = warehouse.Name
        }
    }
}
