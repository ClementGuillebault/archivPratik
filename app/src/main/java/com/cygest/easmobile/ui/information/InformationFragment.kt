package com.cygest.easmobile.ui.information

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.cygest.easmobile.*
import com.cygest.easmobile.libs.BarcodeLaserSdk
import com.cygest.easmobile.libs.BarcodeResultInterface
import com.google.android.material.floatingactionbutton.FloatingActionButton

class InformationFragment : Fragment(), BarcodeResultInterface {

    private lateinit var informationViewModel: InformationViewModel
    private var scanOn: Boolean = false

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        informationViewModel =
                ViewModelProvider(this).get(InformationViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_information, container, false)

        val fab: FloatingActionButton = root.findViewById(R.id.fab_scanning)
        fab.setOnClickListener {
            Scan()
        }
//        val textView: TextView = root.findViewById(R.id.text_home)
//        informationViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }

    fun Scan() {
        if (!scanOn) {
            BarcodeLaserSdk(Activity()).start()
            scanOn = true
            cleanScreen()
        }
        else {
            BarcodeLaserSdk(Activity()).stop()
            scanOn = false
        }
    }

    fun cleanScreen() {
        TODO()
    }

    override fun getResult(result: String) {

    }
}