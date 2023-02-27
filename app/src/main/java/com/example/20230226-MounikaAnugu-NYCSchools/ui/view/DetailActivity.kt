package com.example.demomvvmflow.ui.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.demomvvmflow.data.model.user.response.SatResult
import com.example.demomvvmflow.databinding.ActivitySatDetailsBinding
import com.example.demomvvmflow.ui.viewmodel.NYCSchoolViewModel
import kotlinx.coroutines.flow.collectLatest

class DetailActivity : AppCompatActivity() {
    private lateinit var nycViewModel: NYCSchoolViewModel
    private lateinit var binding: ActivitySatDetailsBinding
    var dbn: String = ""
    var schoolName: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySatDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dbn = intent?.extras?.getString("DBN", "") ?: ""
        schoolName = intent?.extras?.getString("schoolName", "") ?: ""
        try {
            (this as AppCompatActivity).supportActionBar?.title = schoolName
        } catch (e: Exception) {
            e.printStackTrace()
        }
        initViews()
        initObservers()
    }

    private fun initObservers() {
        lifecycleScope.launchWhenCreated {
            nycViewModel.satDataSharedFlow.collectLatest { list ->
                val data: SatResult? = list.findLast { it.dbn?.equals(dbn) == true }
                data.let {
                    binding.valDBN.text = it?.dbn
                    binding.valTestTaker.text = it?.num_of_sat_test_takers
                    binding.valCriticalReading.text = it?.sat_critical_reading_avg_score
                    binding.valMaths.text = it?.sat_math_avg_score
                    binding.valWriting.text = it?.sat_writing_avg_score
                }
                if (data == null) {
                    Toast.makeText(this@DetailActivity, "No record found", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        lifecycleScope.launchWhenCreated {
            nycViewModel.errorSharedFlow.collectLatest {
                Toast.makeText(this@DetailActivity, "" + it, Toast.LENGTH_SHORT)
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
        nycViewModel.getSatResult(binding.root)
    }
}