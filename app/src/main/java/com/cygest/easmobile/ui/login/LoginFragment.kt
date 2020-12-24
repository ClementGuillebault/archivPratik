package com.cygest.easmobile.ui.login

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.cygest.easmobile.*
import com.cygest.easmobile.libs.WebService
import com.google.gson.internal.LinkedTreeMap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class LoginFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_login, container, false)
        var btnConnection: Button = view.findViewById(R.id.btnFragmentLoginConnection)
        val loginValue: EditText = view.findViewById(R.id.editTextLoginFragmentLogin)
        val pwdValue: EditText = view.findViewById(R.id.editTextLoginFragmentPwd)
        btnConnection.setOnClickListener { view ->
            lifecycleScope.launch() {
                tryToAuthenticate(loginValue.text.toString(), "4445423146494E")
            }
        }
        return view
    }

    suspend fun tryToAuthenticate(login: String, pwd: String) {
        withContext(Dispatchers.Main) {
            val request = ServiceBuilder.buildService(WebService::class.java)
            val ctx: Context = context ?: return@withContext
            val r = HttpBuilder().build(WebService::class.java, ctx)
            r.getToken(login, pwd).enqueue(object: Callback<Any> {
                override fun onResponse(call: Call<Any>, response: Response<Any>) {
                    if (response.body() != null) {
                        val accessToken: String = (response.body() as LinkedTreeMap<*, *>)["access_token"].toString()
                        Utility.developerlog(ctx, "token: $accessToken")
                        Constants.TOKEN = accessToken
                        CacheMemory.saveToken(ctx, accessToken)
                        getUser()
                    }
                    else {
                        Utility.developerlog(ctx, "Probleme avec récupération du token")

                    }
                    // checkUser(user)
                }

                override fun onFailure(call: Call<Any>, t: Throwable) {
                    Utility.developerlog(ctx, t.message)
                }
            })
        }
    }

    fun getUser() {
        val ctx = context
        if (ctx != null) {
            AuthRepository().tryToAuthenticate(ctx)
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