package com.example.demomvvmflow.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.demomvvmflow.data.base.remote.BaseService
import com.example.demomvvmflow.repository.NYCSchoolRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

open class NYCSchoolViewModelTest {
    @Rule
    @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    var nycSchoolRepository: NYCSchoolRepository? = null
    private var nycSchoolViewModel: NYCSchoolViewModel? = null

    @Before
    fun setupNYCSchoolViewModel() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.openMocks(this)

        // Get a reference to the class under test
        nycSchoolViewModel = NYCSchoolViewModel()
        nycSchoolRepository = BaseService().getBaseApi()?.let { NYCSchoolRepository(it) }
    }

    @Test
    fun `Fetch NYC School list and having data`() {
        runBlocking {
            nycSchoolRepository?.getNYCSchoolNames()?.collectLatest {
                Assert.assertFalse(it.isEmpty())
            }
        }
    }

    @Test
    fun `Fetch SAT Result and having data`() {
        runBlocking {
            nycSchoolRepository?.getSatResult()?.collectLatest {
                Assert.assertFalse(it.isEmpty())
            }
        }
    }
}