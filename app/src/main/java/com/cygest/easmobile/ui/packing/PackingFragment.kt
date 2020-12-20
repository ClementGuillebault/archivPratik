package com.cygest.easmobile.ui.packing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cygest.easmobile.R

class PackingFragment : Fragment() {

    private lateinit var packingViewModel: PackingViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        packingViewModel =
                ViewModelProvider(this).get(PackingViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_packing, container, false)
//        val textView: TextView = root.findViewById(R.id.text_home)
//        packingViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }
}