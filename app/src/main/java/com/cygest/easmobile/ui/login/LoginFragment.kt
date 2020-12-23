package com.cygest.easmobile.ui.login

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.cygest.easmobile.*
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_login, container, false)
        var inputBarcode: TextInputEditText = view.findViewById(R.id.login_text_input)

        return view
    }

    suspend fun tryToAuthenticate(barcode: String) {
        var user = User()
        user.password = barcode
        withContext(Dispatchers.Main) {
            val request = ServiceBuilder.buildService(WebService::class.java)
            request.tryToAuthenticateByBarcode("", user.password).enqueue(object :
                Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    Utility.developerlog(Activity(), "Récupération de user OK.user id =" + response.body()?.id)
                    user = response.body()!!
                    checkUser(user)
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Utility.developerlog(Activity(), t.message)
                }
            })
        }
    }

    fun checkUser(user: User) {
        if (user.isValid) {
            user.create(Activity())
            findNavController().navigate(R.id.nav_host_fragment)
        }
        else {
            Toast.makeText(Activity(), "barcode incorrect", Toast.LENGTH_LONG).show()
        }
    }
}