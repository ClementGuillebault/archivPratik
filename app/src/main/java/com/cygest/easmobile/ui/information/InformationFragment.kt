package com.cygest.easmobile.ui.information

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cygest.easmobile.*
import com.cygest.easmobile.libs.BarcodeLaserSdk
import com.cygest.easmobile.libs.BarcodeResultInterface
import com.cygest.easmobile.ui.warehouse.WarehouseAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.google.gson.internal.LinkedTreeMap

class InformationFragment : Fragment(), BarcodeResultInterface {

    private lateinit var informationViewModel: InformationViewModel
    private lateinit var txtInformationNoAction: TextView
    private lateinit var fab: FloatingActionButton

    private var scanOn: Boolean = false
    var barcodeLaserSdk: BarcodeLaserSdk? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_information, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        informationViewModel =
                ViewModelProvider(this).get(InformationViewModel::class.java)
        txtInformationNoAction = view.findViewById(R.id.txt_information)
        val cbScanned: TextView = view.findViewById(R.id.cbScanned)

        informationViewModel.informations.observe(viewLifecycleOwner, Observer {
            val data = it as LinkedTreeMap<*, *>
            val data2 = Gson().fromJson(Gson().toJson(it), ResponseFromAPI::class.java)
            if (!data2.IsOk) {
                txtInformationNoAction.text = data2.Message
                cbScanned.text = "EMP00001"
            }
        })
        barcodeLaserSdk = BarcodeLaserSdk.getInstance(requireContext())
    }

    fun cleanScreen() {

    }

    override fun getResult(result: String) {
//        try {
//            informationViewModel.informations.observe(viewLifecycleOwner, Observer {
//                info = it
//            })
//        }
//        catch (t: Throwable) {
//            Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_LONG).show()
//        }
    }

    override fun onCameraReleased() {
        // nothing
    }

}