package com.cygest.easmobile.ui.warehouse

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.cygest.easmobile.R
import dagger.hilt.android.AndroidEntryPoint

class WarehouseAdapter(private val listOfWarehouse: List<Warehouse>) :
        RecyclerView.Adapter<WarehouseAdapter.WareHouseViewHolder>() {
    var onItemClick: ((Warehouse) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WareHouseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return WareHouseViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: WareHouseViewHolder, position: Int) {
        val warehouse = listOfWarehouse[position]
        holder.bind(warehouse)
    }

    override fun getItemCount(): Int = listOfWarehouse.size ?: 0

    inner class WareHouseViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.warehouse_list_item, parent, false)) {
        private var mTitleView: TextView? = null
        private var mImg: ImageView? = null

        init {
            mTitleView = itemView.findViewById(R.id.list_warehouses_title)
            itemView.setOnClickListener(View.OnClickListener {
                onItemClick?.invoke(listOfWarehouse[adapterPosition])
            })
        }

        fun bind(warehouse: Warehouse) {
            mTitleView?.text = warehouse.Name
        }
    }
}
