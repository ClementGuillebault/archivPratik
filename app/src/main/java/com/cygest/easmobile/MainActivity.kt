package com.cygest.easmobile

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (checkPermissions()) {
            // checkForUpdate() pour plus tard

            val user: User = CacheMemory.getUser(this)

            if (user.isValid) {
                val toolbar: Toolbar = findViewById(R.id.toolbar)
                setSupportActionBar(toolbar)

                val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
                val navView: NavigationView = findViewById(R.id.nav_view)
                val navController = findNavController(R.id.nav_host_fragment)

                appBarConfiguration = AppBarConfiguration(setOf(
                    R.id.nav_home, R.id.nav_information, R.id.nav_location,
                    R.id.nav_packing, R.id.nav_warehouse, R.id.nav_settings,
                    R.id.nav_help), drawerLayout)
                setupActionBarWithNavController(navController, appBarConfiguration)
                navView.setupWithNavController(navController)
            }
        }

        if (savedInstanceState == null) {
            findNavController(R.id.nav_host_fragment).navigate(R.id.nav_logout)
        }
    }

    private fun checkPermissions(): Boolean {
        val permission = checkSelfPermission(Manifest.permission.INTERNET)
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // makeRequest()
            return false
        }

        return true
    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        when (requestCode) {
//            1 -> {
//                if (grantResults.isNotEmpty() && grantResults[0] ==
//                    PackageManager.PERMISSION_GRANTED) {
//                    if ((checkSelfPermission(Manifest.permission.INTERNET) ===
//                                PackageManager.PERMISSION_GRANTED)) {
//                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
//                    }
//                } else {
//                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
//                }
//                return
//            }
//        }
//    }
//
//    private fun makeRequest() {
//        requestPermissions(arrayOf(Manifest.permission.INTERNET), 1)
//    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}