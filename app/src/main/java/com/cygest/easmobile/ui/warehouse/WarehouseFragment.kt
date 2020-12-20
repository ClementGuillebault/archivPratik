package com.cygest.easmobile.ui.warehouse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cygest.easmobile.R

class WarehouseFragment : Fragment() {

    private lateinit var warehouseViewModel: WarehouseViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        warehouseViewModel =
                ViewModelProvider(this).get(WarehouseViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_warehouse, container, false)
//        val textView: TextView = root.findViewById(R.id.text_home)
//        warehouseViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }
}