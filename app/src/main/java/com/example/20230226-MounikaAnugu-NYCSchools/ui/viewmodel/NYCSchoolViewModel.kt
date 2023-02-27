package com.example.demomvvmflow.ui.viewmodel

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.viewModelScope
import com.example.demomvvmflow.data.base.remote.BaseService
import com.example.demomvvmflow.data.model.user.response.NYChighSchools
import com.example.demomvvmflow.data.model.user.response.SatResult
import com.example.demomvvmflow.repository.NYCSchoolRepository
import com.example.demomvvmflow.util.isNetworkAvailable
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class NYCSchoolViewModel : BaseObservableViewModel() {

    private val nycRepository = BaseService().getBaseApi()?.let { NYCSchoolRepository(it) }

    private val _schoolListSharedFlow = MutableSharedFlow<List<NYChighSchools>>()
    val schoolListSharedFlow = _schoolListSharedFlow as SharedFlow<List<NYChighSchools>>

    private val _satDataSharedFlow = MutableSharedFlow<List<SatResult>>()
    val satDataSharedFlow = _satDataSharedFlow as SharedFlow<List<SatResult>>

    private val _loadingSharedFlow = MutableSharedFlow<Boolean>()
    val loadingSharedFlow = _loadingSharedFlow as SharedFlow<Boolean>

    private val _errorSharedFlow = MutableSharedFlow<String>()
    val errorSharedFlow = _errorSharedFlow as SharedFlow<String>

    fun getNYCSchoolNames(root: ConstraintLayout) {
        if (isNetworkAvailable()) {
            viewModelScope.launch {
                _loadingSharedFlow.emit(true)
            }

            viewModelScope.launch {
                nycRepository?.getNYCSchoolNames()?.catch {
                    _loadingSharedFlow.emit(false)
                    _errorSharedFlow.emit(it.message.toString())

                }?.collect {
                    _loadingSharedFlow.emit(false)
                    _schoolListSharedFlow.emit(it)
                }
            }
        } else {
            Snackbar.make(
                root,
                "Please check the Internet connection and try again.",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    fun getSatResult(root: ConstraintLayout) {
        if (isNetworkAvailable()) {
            viewModelScope.launch {
                _loadingSharedFlow.emit(true)
            }

            viewModelScope.launch {
                nycRepository?.getSatResult()?.catch {
                    _loadingSharedFlow.emit(false)
                    _errorSharedFlow.emit(it.message.toString())

                }?.collect {
                    _loadingSharedFlow.emit(false)
                    _satDataSharedFlow.emit(it)
                }
            }
        } else {
            Snackbar.make(
                root,
                "Please check the Internet connection and try again.",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }
}