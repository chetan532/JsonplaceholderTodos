package com.jsontodos.jsonplaceholdertodos

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.jsontodos.jsonplaceholdertodos.databinding.ActivityMainBinding
import com.jsontodos.jsonplaceholdertodos.presentation.home.ViewStateAdapter
import com.jsontodos.jsonplaceholdertodos.presentation.main.MainViewModel
import com.jsontodos.jsonplaceholdertodos.presentation.main.MainViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        WindowCompat.setDecorFitsSystemWindows(window, false)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Pending"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Completed"))

        val adapter = ViewStateAdapter(supportFragmentManager,lifecycle)
        binding.pager.adapter = adapter

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    binding.pager.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                //Not
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                //Not
            }

        })

        (application as MainApplication).applicationComponent.inject(this)

        mainViewModel = ViewModelProvider(this, mainViewModelFactory)[MainViewModel::class.java]

        mainViewModel.getTodosData()
    }
}