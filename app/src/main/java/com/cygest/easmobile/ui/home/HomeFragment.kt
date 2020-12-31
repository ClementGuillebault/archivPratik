package com.cygest.easmobile.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TextView
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.cygest.easmobile.R
import com.cygest.easmobile.TabAdapter
import com.cygest.easmobile.ui.information.InformationFragment
import com.cygest.easmobile.ui.location.LocationFragment
import com.cygest.easmobile.ui.packing.PackingFragment
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private val tabLayout by lazy { view?.findViewById<TabLayout>(R.id.tabMainLayout) }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
//        val textView: TextView = root.findViewById(R.id.text_home)
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

        val tabAdapter = TabAdapter(requireActivity())
        tabAdapter.add(InformationFragment(), getString(R.string.information))
        tabAdapter.add(LocationFragment(), getString(R.string.location))
        tabAdapter.add(PackingFragment(), getString(R.string.packing))

        val viewPager = view?.findViewById<ViewPager2>(R.id.pager)
        viewPager?.adapter = tabAdapter

        if (tabLayout != null && viewPager != null) {
            TabLayoutMediator(tabLayout!!, viewPager) { tab, position ->
                tab.text = "test ${position}"
            }.attach()
        }

        tabLayout?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                // Handle tab select
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Handle tab reselect
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Handle tab unselect
            }
        })

        return root
    }
}