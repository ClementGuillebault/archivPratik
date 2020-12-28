package com.cygest.easmobile.ui.login

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
import kotlinx.coroutines.*

class LoginFragment : Fragment() {

    private lateinit var btnConnection: Button
    private lateinit var loginTextView: EditText
    private lateinit var pwdTextView: EditText

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_login, container, false)
        btnConnection = view.findViewById(R.id.btnFragmentLoginConnection)
        loginTextView = view.findViewById(R.id.editTextLoginFragmentLogin)
        pwdTextView = view.findViewById(R.id.editTextLoginFragmentPwd)

        btnConnection.setOnClickListener {
            lifecycleScope.launch() {
                authenticate(loginTextView.text.toString(), "4445423146494E")
            }
        }
        return view
    }

    private suspend fun authenticate(login: String, pwd: String) {
        try {
            AuthRepository().getToken(login, pwd)
            checkUser(AuthRepository().getUser())
        }
        catch (t: Throwable) {
            Toast.makeText(requireContext(), "error: ${t.message}", Toast.LENGTH_LONG).show()
            throw t
        }
    }

    private fun checkUser(user: User) {
        if (user.Id != 0) {
            user.isValid = true
            user.create(requireContext())
            findNavController().navigateUp()
        }
        else {
            Toast.makeText(requireContext(), "Authentication error", Toast.LENGTH_LONG).show()
        }
    }
}