package com.cygest.easmobile.ui.information

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.cygest.easmobile.*
import com.cygest.easmobile.libs.BarcodeLaserSdk
import com.cygest.easmobile.libs.BarcodeResultInterface
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.google.gson.internal.LinkedTreeMap

class InformationFragment : Fragment(), BarcodeResultInterface {

    private val viewModel by viewModels<InformationViewModel>()

    private val txtInformationNoAction by lazy { view?.findViewById<TextView>(R.id.txt_information) }
    private val fab by lazy { view?.findViewById<FloatingActionButton>(R.id.txt_information) }

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

        val cbScanned: TextView = view.findViewById(R.id.cbScanned)

        viewModel.informations.observe(viewLifecycleOwner, Observer {
            val data = it as LinkedTreeMap<*, *>
            val data2 = Gson().fromJson(Gson().toJson(it), ResponseFromAPI::class.java)
            if (!data2.IsOk) {
                txtInformationNoAction?.text = data2.Message
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