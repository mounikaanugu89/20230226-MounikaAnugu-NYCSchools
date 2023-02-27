package com.example.demomvvmflow.ui.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demomvvmflow.databinding.ActivityMainBinding
import com.example.demomvvmflow.ui.adapter.CustomAdapter
import com.example.demomvvmflow.ui.viewmodel.NYCSchoolViewModel
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity() {
    private lateinit var nycViewModel: NYCSchoolViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        initObservers()

    }

    private fun initObservers() {
        lifecycleScope.launchWhenCreated {
            nycViewModel.schoolListSharedFlow.collectLatest { list ->
                // This will pass the ArrayList to our Adapter
                val adapter = CustomAdapter(list)
                binding.rvList.adapter = adapter
            }
        }

        lifecycleScope.launchWhenCreated {
            nycViewModel.errorSharedFlow.collectLatest {
                Toast.makeText(this@MainActivity, "" + it, Toast.LENGTH_SHORT)
                    .show()
            }
        }

        lifecycleScope.launchWhenCreated {
            nycViewModel.loadingSharedFlow.collectLatest {
                if (it) {
                    binding.progressBar.visibility = View.VISIBLE
                } else {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
    }

    private fun initViews() {
        nycViewModel = ViewModelProvider(this)[NYCSchoolViewModel::class.java]
        nycViewModel.getNYCSchoolNames(binding.root)

        binding.rvList.layoutManager = LinearLayoutManager(this)

    }

}